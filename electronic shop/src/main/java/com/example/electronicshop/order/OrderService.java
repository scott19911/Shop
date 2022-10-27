package com.example.electronicshop.order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface OrderService {
    /**
     * @return - all delivery and payment options
     */
    DeliveryAndPayment getDeliveryPayment();

    /**
     * Create new order
     *
     * @param request - order information request
     * @param response- for the request
     */
    void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * Checks all the necessary data to create an order
     *
     * @param request  - order request
     * @param response - for the request
     * @throws IOException when can't send redirect response
     */
    OrderDetails orderVerifier(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void showUserOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
