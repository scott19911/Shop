package com.example.electronicshop.order.processing;

import com.example.electronicshop.dao.OrderRepository;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.order.UserOrders;
import com.example.electronicshop.validate.ValidateOrder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

public class ManagerService {
    private final OrderRepository orderRepository;
    private final TransactionManager transactionManager;

    public ManagerService(OrderRepository orderRepository, TransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public void showUserOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserOrders userOrders = transactionManager.doInTransaction(() -> {
            UserOrders userOrder = new UserOrders();
            userOrder.setOrderInfoList(orderRepository.getAllOrders());
            userOrder.setOrderProduct(orderRepository.getAllOrderProduct());
            userOrder.setStatusMap(orderRepository.getStatus());
            return userOrder;
        }, Connection.TRANSACTION_READ_COMMITTED);
        session.setAttribute("orderInfo", userOrders);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("orders.jsp");
        requestDispatcher.forward(request, response);
    }
}
