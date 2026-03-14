package com.abbtech.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.abbtech.controller.LoginController.USER_NAME;

@WebFilter(urlPatterns = {"/profile", "/logout"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute(USER_NAME) != null;

        if (!loggedIn) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: please log in first");
            return;
        }
        chain.doFilter(req, resp);
    }

}
