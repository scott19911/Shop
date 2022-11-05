package com.example.electronicshop.servlets;

import com.example.electronicshop.service.UserBlockerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.electronicshop.utils.ContextListener.UNBLOCK_SERVICE;

@WebServlet("/userList")
public class UserListServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBlockerService userBlockerService = (UserBlockerService) req.getServletContext().getAttribute(UNBLOCK_SERVICE);
        userBlockerService.showUserList(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserBlockerService userBlockerService = (UserBlockerService) req.getServletContext().getAttribute(UNBLOCK_SERVICE);
        userBlockerService.unblockUser(req, resp);
    }
}
