package com.example.electronicshop.servlets;

import com.example.electronicshop.dao.OrderRepositoryImpl;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.order.processing.ManagerService;
import com.example.electronicshop.validate.ValidateOrderImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.electronicshop.constants.Pages.INDEX_PAGE;
import static com.example.electronicshop.constants.Pages.SHOP_PAGE;

@WebServlet("/confirm")
public class ConfirmOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerService managerService = new ManagerService(new OrderRepositoryImpl(),new TransactionManager());
        managerService.showUserOrders(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect(INDEX_PAGE);
    }
}
