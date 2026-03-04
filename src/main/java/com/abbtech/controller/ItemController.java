package com.abbtech.controller;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.repository.ItemDurableRepository;
import com.abbtech.service.abstracts.ItemService;
import com.abbtech.service.concretes.ItemServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
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
        itemService = new ItemServiceImpl(new ItemDurableRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        String nameParameter = request.getParameter("name");

        if (nameParameter != null && !nameParameter.isBlank()) {
            response.setStatus(HttpServletResponse.SC_OK);
            writer.println(
                    OBJECT_MAPPER.writeValueAsString(
                            itemService.getByName(nameParameter)
                    )
            );
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            writer.println(
                    OBJECT_MAPPER.writeValueAsString(
                            itemService.getAll()
                    )
            );
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String nameParameter = request.getParameter("name");
        if (nameParameter != null && !nameParameter.isBlank()) {
            response.setStatus(HttpServletResponse.SC_OK);
            itemService.deleteByName(nameParameter);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String nameParameter = request.getParameter("name");
        if (nameParameter != null && !nameParameter.isBlank()) {
            RequestItemDto requestItemDto = OBJECT_MAPPER.readValue(request.getReader(), RequestItemDto.class);
            response.setStatus(HttpServletResponse.SC_OK);
            itemService.updateByName(nameParameter, requestItemDto);
        }
    }
}
