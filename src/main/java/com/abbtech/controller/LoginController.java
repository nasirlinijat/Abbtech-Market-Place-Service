package com.abbtech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.Instant;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    public static final String USER_NAME = "admin";
    private static final String PASSWORD = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() ||
            password == null || password.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (!username.equals(USER_NAME) || !password.equals(PASSWORD)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession newSession = req.getSession(true);
        newSession.setAttribute(USER_NAME, PASSWORD);
        newSession.setMaxInactiveInterval(30 * 60);
        String loginTime = Instant.now().toString();
        Cookie lastLogin = new Cookie("lastLogin", loginTime);
        lastLogin.setMaxAge(30 * 24 * 60 * 60);
        lastLogin.setHttpOnly(true);
        resp.addCookie(lastLogin);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(String.format("User: %s | Session: %s | Login time: %s",
                USER_NAME, newSession.getId(), loginTime));    }
}
