package com.example.electronicshop.order.processing;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface ManagerService {
    /**
     * Show all available orders
     * @param request - for getting orders
     * @param response on request
     */
    void showUserOrders(HttpServletRequest request, HttpServletResponse response);

    /**
     * Set new status for order
     * @param request - data where we need to change status
     * @return - map with error
     */
    Map<String, String> setNewStatus(HttpServletRequest request);
}

