package com.example.electronicshop.service.impl;

import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.service.UserBlockerService;
import com.example.electronicshop.users.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static com.example.electronicshop.dao.MySqlUserDao.USER_ID;


public class UserBlockerServiceImpl implements UserBlockerService {
    private final UserDao userDao;
    private final TransactionManager transactionManager;
    public static final String USER_LIST = "userList";

    public UserBlockerServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void showUserList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<User> userList = transactionManager.doWithoutTransaction(userDao::getAllUser);
        session.setAttribute(USER_LIST, userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userList.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unblockUser(HttpServletRequest request, HttpServletResponse response) {
        int userId = 0;
        try {
            userId = Integer.parseInt(request.getParameter(USER_ID));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        int finalUserId = userId;
        transactionManager.doWithoutTransaction(() -> {
            userDao.unblockUser(finalUserId);
            return true;
        });
        try {
            response.sendRedirect("/userList");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
