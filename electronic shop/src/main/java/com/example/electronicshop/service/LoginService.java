package com.example.electronicshop.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * This class is responsible for user authorization
 */
public interface LoginService {
    /**
     * If the user is already registered and entered the correct data, then the login is performed
     * @param request -with user's email and password
     * @param response - in case of successful login redirects to the store page
     * @throws IOException -when can't sendRedirect
     */
    void login(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
