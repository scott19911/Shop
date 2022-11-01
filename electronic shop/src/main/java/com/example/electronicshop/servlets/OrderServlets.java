package com.example.electronicshop.servlets;

import com.example.electronicshop.order.OrderService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.electronicshop.utils.ContextListener.ORDER_SERVICE;

@WebServlet("/order")
public class OrderServlets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(ORDER_SERVICE);
        orderService.createOrder(req,resp);
    }
}
