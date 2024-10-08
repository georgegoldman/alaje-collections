package com.hydrogenhr.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:36 PM
 */
@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class LogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        LocalDateTime date = LocalDateTime.now();
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        log.info("------------------------------------------------------------------------");
        log.info("START LOGFILTER: {} - {}:{}{}\nRequest:", date, request.getLocalAddr(), request.getLocalPort(), request.getServletPath());
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = (String) headers.nextElement();
            log.info("\tHeader: {} = {}", headerName, request.getHeader(headerName));
        }
        log.info("\n");
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameterName = (String) parameters.nextElement();
            log.info("\tParameter: {}: {}", parameterName, request.getParameter(parameterName));
        }
        log.info("\n");
        Enumeration<String> attributes = request.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attributeName = (String) attributes.nextElement();
            log.info("\tAttribute: {}: {}", attributeName, request.getParameter(attributeName));
        }

        filterChain.doFilter(requestWrapper, responseWrapper);

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                response.getCharacterEncoding());

        log.info("Request Body: {}\n", requestBody);
        log.info("Response Body: {}\n", responseBody);
        log.info("\n");
        Collection<String> responseHeaders = response.getHeaderNames();
        responseHeaders.forEach(x -> {
            log.info("\tHeader: {}: {}", x, response.getHeader(x));
            log.info("\tHeader Status: {}: {}", x, response.getStatus());
        });
        log.info("\n\n");


        log.info("END LOG FILTER");
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            log.error("logFilterError: ", e);
        }
        return "";
    }
}
