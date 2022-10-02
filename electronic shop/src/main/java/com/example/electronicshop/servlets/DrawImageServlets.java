package com.example.electronicshop.servlets;

import com.example.electronicshop.service.ImageService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.electronicshop.service.ImageServiceImpl.PRODUCT_IMG;


/**
 * Renders the user's avatar on the jsp page
 */
@WebServlet("/drawAvatar")
public class DrawImageServlets extends HttpServlet {
    public static final String IMAGE_STORAGE_PATH = "ImageStorage";
    public static final String IMAGE_SERVICE = "ImageService";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ImageService imageService = (ImageService) req.getServletContext().getAttribute(IMAGE_SERVICE);
        if (req.getParameterMap().containsKey(PRODUCT_IMG)) {
            imageService.drawImage(req, resp);
        } else {
            imageService.drawAvatar(req, resp);
        }
    }
}
