package com.mohit.demo.Filter;

import jakarta.servlet.*;

import java.io.IOException;

public class DummyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Request Entered into Dummy filter");

        chain.doFilter(request,response);

        System.out.println("Request Exiting into Dummy filter");
    }
}
