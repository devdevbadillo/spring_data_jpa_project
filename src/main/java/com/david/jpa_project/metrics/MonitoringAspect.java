package com.david.jpa_project.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class MonitoringAspect {

    private final MeterRegistry meterRegistry;
    private final ConcurrentHashMap<String, Timer> timers = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Counter> counters = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Counter> errorCounters = new ConcurrentHashMap<>();

    public MonitoringAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Around("@annotation(monitored)")
    public Object monitorMethod(ProceedingJoinPoint joinPoint, Monitored monitored) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        String metricName = monitored.value().isEmpty()
                ? className + "." + methodName
                : monitored.value();

        Timer timer = timers.computeIfAbsent(metricName, name ->
                Timer.builder("method.execution.time")
                        .description(monitored.description())
                        .tag("class", className)
                        .tag("method", methodName)
                        .tag("metric", name)
                        .register(meterRegistry)
        );

        Counter invocationCounter = counters.computeIfAbsent(metricName, name ->
                Counter.builder("method.invocation.count")
                        .description("Número de invocaciones del método")
                        .tag("class", className)
                        .tag("method", methodName)
                        .tag("metric", name)
                        .register(meterRegistry)
        );

        invocationCounter.increment();

        log.info("Monitoreando: {}.{}", className, methodName);

        Timer.Sample sample = Timer.start(meterRegistry);

        try {
            Object result = joinPoint.proceed();
            sample.stop(timer);

            log.info("Método {}.{} ejecutado exitosamente", className, methodName);
            return result;

        } catch (Throwable throwable) {
            sample.stop(timer);

            if (monitored.recordExceptions()) {

                Counter errorCounter = errorCounters.computeIfAbsent(metricName + ".error", name ->
                        Counter.builder("method.error.count")
                                .description("Número de errores en el método")
                                .tag("class", className)
                                .tag("method", methodName)
                                .tag("metric", metricName)
                                .tag("exception", throwable.getClass().getSimpleName())
                                .register(meterRegistry)
                );
                errorCounter.increment();

                log.error("Error en {}.{}: {}", className, methodName, throwable.getMessage());
            }

            throw throwable;
        }
    }
}
