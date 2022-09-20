package com.example.electronicshop.registration;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *Receives a request to render a captcha
 */
public class CaptchaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DrawCaptcha drawCaptcha = new DrawCaptcha();
        drawCaptcha.draw(request, response);
    }
}