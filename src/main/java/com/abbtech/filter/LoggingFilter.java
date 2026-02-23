package com.abbtech.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoggingFilter is called");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("LoggingFilter is finished");
    }
}
