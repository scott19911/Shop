package com.example.electronicshop.servlets;

import com.example.electronicshop.service.ImageService;
import com.example.electronicshop.service.ImageServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Renders the user's avatar on the jsp page
 */
@WebServlet("/drawAvatar")
public class DrawImage extends HttpServlet {
    public static final String IMAGE_STORAGE_PATH = "ImageStorage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ImageService imageService = new ImageServiceImpl();
        imageService.drawImage(req,resp);
    }
}
