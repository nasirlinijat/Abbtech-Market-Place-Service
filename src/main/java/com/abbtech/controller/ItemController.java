package com.abbtech.controller;

import com.abbtech.repository.ItemInMemoryRepositoryImpl;
import com.abbtech.service.abstracts.ItemService;
import com.abbtech.service.concretes.ItemServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ItemController", urlPatterns = "/items")
public class ItemController extends HttpServlet {
    private ItemService itemService;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void init() {
        itemService = new ItemServiceImpl(new ItemInMemoryRepositoryImpl());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(OBJECT_MAPPER.writeValueAsString(itemService.getAllItems()));
    }
}
