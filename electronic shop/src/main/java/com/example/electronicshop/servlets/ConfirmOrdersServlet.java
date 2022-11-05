package com.example.electronicshop.servlets;

import com.example.electronicshop.order.processing.ManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.electronicshop.utils.ContextListener.MANAGER_SERVICE;

@WebServlet("/confirm")
public class ConfirmOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerService managerService = (ManagerService) req.getServletContext().getAttribute(MANAGER_SERVICE);
        managerService.showUserOrders(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ManagerService managerService = (ManagerService) req.getServletContext().getAttribute(MANAGER_SERVICE);
        Map<String,String> error = managerService.setNewStatus(req);
        HttpSession session = req.getSession();
        session.setAttribute("err",error);
        resp.sendRedirect("/confirm");
    }
}
