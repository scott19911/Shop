package com.example.electronicshop.servlets;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import static com.example.electronicshop.constants.Pages.INDEX_PAGE;
import static com.example.electronicshop.service.UploadAvatar.SPECIFIC_USER;

@WebServlet("/LogOff")
public class LogOff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute(SPECIFIC_USER);
        resp.sendRedirect(INDEX_PAGE);
    }
}
