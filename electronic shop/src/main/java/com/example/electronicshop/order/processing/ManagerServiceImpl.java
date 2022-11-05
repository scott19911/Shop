package com.example.electronicshop.order.processing;

import com.example.electronicshop.dao.OrderRepository;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.order.UserOrders;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import static com.example.electronicshop.cart.CartServiceImpl.ID;
import static com.example.electronicshop.constants.Pages.ORDER_PAGE;
import static com.example.electronicshop.dao.OrderRepositoryImpl.STATUS;
import static com.example.electronicshop.order.OrderServiceImpl.ORDER_INFO;

public class ManagerServiceImpl implements ManagerService {
    private final OrderRepository orderRepository;
    private final TransactionManager transactionManager;
    public static final String DESCRIPTION = "description";
    private int orderID;
    private int orderStatus;

    public ManagerServiceImpl(OrderRepository orderRepository, TransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void showUserOrders(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserOrders userOrders = transactionManager.doInTransaction(() -> {
            UserOrders userOrder = new UserOrders();
            userOrder.setOrderInfoList(orderRepository.getAllOrders());
            userOrder.setOrderProduct(orderRepository.getAllOrderProduct());
            userOrder.setStatusMap(orderRepository.getStatus());
            return userOrder;
        }, Connection.TRANSACTION_READ_COMMITTED);
        session.setAttribute(ORDER_INFO, userOrders);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ORDER_PAGE);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> setNewStatus(HttpServletRequest request) {
        String description = request.getParameter(DESCRIPTION);
        Map<String, String> errorMap = new HashMap<>();
        try {
            orderID = Integer.parseInt(request.getParameter(ID));
            orderStatus = Integer.parseInt(request.getParameter(STATUS));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        if (description == null || description.isBlank()) {
            errorMap.put(String.valueOf(orderID), "Can't be empty");
        }
        if (errorMap.isEmpty()) {
            transactionManager.doWithoutTransaction(() -> {
                orderRepository.updateStatus(orderID, orderStatus, description);
                return true;
            });
        }
        return errorMap;
    }
}
