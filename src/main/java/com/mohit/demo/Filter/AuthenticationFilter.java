package com.mohit.demo.Filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@Component
//@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Request Entered into Authentication filter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("token");

        String apiKey = httpRequest.getHeader("api-key");
        System.out.println("API Key: " + apiKey);

        if(apiKey == null || !apiKey.equals("my-key")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.getWriter().write("{\"API-Key\":\"NULL\"}");
            return;
        }


        if(token == null || !token.equals("12345")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Token is missing or invalid");
            return;
        }

        String uri = httpRequest.getRequestURI();

        if(!uri.startsWith("/api")) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.getWriter().write("Access denied");
            return;
        }

        chain.doFilter(request,response);

        System.out.println("Request Exiting into Authentication filter");
    }
}
