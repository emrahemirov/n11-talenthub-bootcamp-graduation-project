//package com.bootcamp.apigateway.filter;
//
//import com.bootcamp.apigateway.model.Log;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class LoggingFilter extends OncePerRequestFilter implements Ordered {
//    @Override
//    public int getOrder() {
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String requestUri = request.getRequestURI();
//        return requestUri.contains("/swagger-ui") || requestUri.contains("/v3/api-docs");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        System.out.println("abcd");
//        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
//        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
//        if (shouldNotFilter(request)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//        filterChain.doFilter(wrappedRequest, wrappedResponse);
//
//
//        System.out.println(wrappedRequest.toString());
//
//        String reqBodyAsStr = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
//        String resBodyAsStr = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
//
//
//        wrappedResponse.copyBodyToResponse();
//
//        Log log = new Log();
//        log.setStatusCode(((Integer) response.getStatus()).toString());
//        log.setMethod(request.getMethod());
//        log.setReqUri(request.getRequestURI());
//        log.setReqBody(reqBodyAsStr);
//        log.setResBody(resBodyAsStr);
//
//
//    }
//
//
//}
