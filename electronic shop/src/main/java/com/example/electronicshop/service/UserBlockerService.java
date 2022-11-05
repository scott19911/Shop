package com.example.electronicshop.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserBlockerService {
    /**
     * Show all registered users
     * @param request - for getting a users
     * @param response on request
     */
    void showUserList(HttpServletRequest request, HttpServletResponse response);

    /**
     * unblock the user and set the time when was unblocked
     * @param request - data
     * @param response - for request
     */
    void unblockUser(HttpServletRequest request, HttpServletResponse response);
}
