package com.bootcamp.apigateway.filter;

import com.bootcamp.apigateway.dto.Log;
import com.bootcamp.apigateway.producer.LogProducer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor

public class LogFilter extends OncePerRequestFilter implements Ordered {

    private final LogProducer logProducer;

    private final List<String> excludedPaths = Arrays.asList(
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-resources",
            "/favicon.ico"
    );


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        if (isRequestForOpenApi(request)) {
            chain.doFilter(request, response);
            return;
        }


        chain.doFilter(wrappedRequest, wrappedResponse);

        byte[] requestBody = wrappedRequest.getContentAsByteArray();
        String requestBodyString = new String(requestBody, StandardCharsets.UTF_8);

        byte[] responseBody = wrappedResponse.getContentAsByteArray();
        String responseBodyString = new String(responseBody, StandardCharsets.UTF_8);


        wrappedResponse.copyBodyToResponse();


        Log log = new Log();
        log.setMethod(request.getMethod());
        log.setStatusCode(((Integer) response.getStatus()).toString());
        log.setReqBody(requestBodyString);
        log.setResBody(responseBodyString);
        log.setReqUri(request.getRequestURI());

        logProducer.sendLog(log);
    }


    private boolean isRequestForOpenApi(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return excludedPaths.stream().anyMatch(requestUri::contains);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
