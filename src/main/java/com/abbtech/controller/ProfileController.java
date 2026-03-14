package com.abbtech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import static com.abbtech.controller.LoginController.USER_NAME;

@WebServlet(urlPatterns = "/profile")
public class ProfileController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        String lastLogin = "N/A";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lastLogin".equals(cookie.getName())) {
                    lastLogin = cookie.getValue();
                    break;
                }
            }
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(String.format("User: %s | Session: %s | Login time: %s",
                USER_NAME, session.getId(), lastLogin));
    }
}
