package com.example.electronicshop.servlets;

import com.example.electronicshop.order.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.example.electronicshop.utils.ContextListener.ORDER_SERVICE;

@WebServlet("/orderInf")
public class OrdersInfoServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(ORDER_SERVICE);
        orderService.showUserOrders(req,resp);
    }
}
