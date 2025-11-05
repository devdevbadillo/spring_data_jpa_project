package com.david.jpa_project.controller.filters;

import com.david.jpa_project.config.audit.RequestContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuditorHeaderFilter extends OncePerRequestFilter {

    private static final String HEADER_NAME = "X-User-Email";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String userEmail = request.getHeader(HEADER_NAME);
        if (userEmail != null && !userEmail.isBlank()) {
            RequestContext.setCurrentUser(userEmail);
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            RequestContext.clear();
        }
    }
}
