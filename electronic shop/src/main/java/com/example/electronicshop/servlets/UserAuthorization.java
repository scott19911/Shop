package com.example.electronicshop.servlets;

import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.dao.UserDaoFactory;
import com.example.electronicshop.service.LoginService;
import com.example.electronicshop.service.LoginUserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.electronicshop.constants.Pages.INDEX_PAGE;
import static com.example.electronicshop.service.LoginUserService.LOGIN_ERROR;
import static com.example.electronicshop.servlets.Registration.DB_TYPE;

@WebServlet("/login")
public class UserAuthorization extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<String, String> error = (Map<String, String>) session.getAttribute(LOGIN_ERROR);
        req.setAttribute(LOGIN_ERROR, error);
        session.removeAttribute(LOGIN_ERROR);
        RequestDispatcher dispatcher = req.getRequestDispatcher(INDEX_PAGE);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        UserDao userDao = userDaoFactory.getUserDao(session.getServletContext().getInitParameter(DB_TYPE));
        LoginService loginService = new LoginUserService(userDao);
        loginService.login(req, resp);
    }
}
