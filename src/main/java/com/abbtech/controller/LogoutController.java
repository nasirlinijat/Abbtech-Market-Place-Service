package com.abbtech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession(false).invalidate();

        Cookie expiredCookie = new Cookie("lastLogin", "");
        expiredCookie.setMaxAge(0);
        resp.addCookie(expiredCookie);

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
