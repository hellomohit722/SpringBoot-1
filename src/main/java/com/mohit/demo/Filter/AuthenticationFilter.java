package com.mohit.demo.Filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Request Entered into Authentication filter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("token");

        String apiKey = httpRequest.getHeader("api-key");

        if(apiKey == null || !apiKey.equals("my-key")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.getWriter().write("API Key is missing or invalid");
            return;
        }


        if(token == null || !token.equals("12345")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Token is missing or invalid");
            return;
        }



        chain.doFilter(request,response);

        System.out.println("Request Exiting into Authentication filter");
    }
}
