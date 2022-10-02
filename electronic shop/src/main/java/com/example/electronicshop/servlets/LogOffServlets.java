package com.example.electronicshop.servlets;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Enumeration;

import static com.example.electronicshop.constants.Pages.INDEX_PAGE;

@WebServlet("/LogOff")
public class LogOffServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enumeration<String> attribute = req.getSession(false).getAttributeNames();
        while (attribute.hasMoreElements()){
            req.getSession(false).removeAttribute(attribute.nextElement());
        }
        resp.sendRedirect(INDEX_PAGE);
    }
}
