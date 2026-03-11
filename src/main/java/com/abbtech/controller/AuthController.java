package com.abbtech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@WebServlet(name = "AuthController", urlPatterns = "/login")
public class AuthController extends HttpServlet {
    private static String username = "admin";
    private static String password = "admin";

    @Override
    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals(AuthController.username) && password.equals(AuthController.password)) {

            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(60);
            session.setAttribute("theme", "dark");
            session.setAttribute("items", List.of("item1", "item2", "item3"));



//            response.setStatus(HttpServletResponse.SC_OK);
//            Cookie cookieUsername = new Cookie("x-user-id", username);
//            Cookie cookie3 = new Cookie("theme", "dark");
//            response.addCookie(cookie3); // Add the third cookie
//
//            cookieUsername.setDomain("localhost");
//            cookieUsername.setHttpOnly(true);
//            cookieUsername.setMaxAge(60);
//            response.addCookie(cookieUsername);

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
