package com.example.electronicshop.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The service is responsible for rendering images stored on the server
 */
public interface ImageService {
    /**
     *  rendering images stored on the server
     * @param req - request to draw an image
     * @param resp - response to a request with a rendered image
     * @throws IOException - when can't read image and create output stream
     */
    void drawImage(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
