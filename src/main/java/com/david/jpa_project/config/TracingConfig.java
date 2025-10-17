package com.david.jpa_project.config; // Changed package name to be consistent with common Java practices

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    public SpanExporter otlpExporter() {
        return OtlpGrpcSpanExporter.builder()
                .setEndpoint("http://localhost:4317")
                .build();
    }

}
