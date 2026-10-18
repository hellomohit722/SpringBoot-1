package com.mohit.demo.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class ResponseBodyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Wrap the real response so the body can be captured instead of sent immediately.
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);

        chain.doFilter(servletRequest, responseWrapper);

        byte[] originalBodyByte = responseWrapper.getContentAsByteArray();

        String originalBody = new String(originalBodyByte);

        String modifiedBody = """
                "originalBody": %s,
                "message": "This is a modified response body"
                """.formatted(originalBody);

        responseWrapper.resetBuffer();
        responseWrapper.getWriter().write(modifiedBody);

        // Copy the wrapper's final body to the real HTTP response so it reaches the client.
        responseWrapper.copyBodyToResponse();
    }
}
