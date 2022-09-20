package com.example.electronicshop.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface RegistrationService {
    /**
     * Checks the entered user data and if they are valid, registers it otherwise sends a message with incorrect data
     *
     * @param req  - entered request
     * @param resp - response to a request
     * @throws java.io.IOException - when can't send redirect
     */
    void registration(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}

