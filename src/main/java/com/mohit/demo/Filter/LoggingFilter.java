package com.mohit.demo.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

//@Component
//@Order(2)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Request Entered into loggin filter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("Request URL: " + httpRequest.getRequestURL());
        System.out.println("Request method: " + httpRequest.getMethod());

        String requestId = UUID.randomUUID().toString();

        // when your request is in downstream, then it become read-only.
        httpResponse.setHeader("X-Request-ID", requestId);


        // if get any error from controller, service layer then
        try {
            chain.doFilter(request,response);
        }
        finally {
        System.out.println("Request Exiting into loggin filter");
        System.out.println("Response status: " + httpResponse.getStatus());
        }



    }
}
