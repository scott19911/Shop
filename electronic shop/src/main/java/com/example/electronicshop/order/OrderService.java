package com.example.electronicshop.order;

import com.example.electronicshop.users.SpecificUser;
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
     * @param orderDetailsDTO - order information
     * @param user            - which create order
     */
    void createOrder(OrderDetailsDTO orderDetailsDTO, SpecificUser user);

    /**
     * Checks all the necessary data to create an order
     *
     * @param request  - order request
     * @param response - for the request
     * @throws IOException when can't send redirect response
     */
    void orderVerifier(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
