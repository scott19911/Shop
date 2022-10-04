package com.example.electronicshop.servlets;

import com.example.electronicshop.service.LoginService;
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
import static com.example.electronicshop.service.impl.LoginUserService.LOGIN_ERROR;


@WebServlet("/login")
public class UserAuthorizationServlets extends HttpServlet {
    public static final String LOGIN_SERVICE = "LoginService";

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
        LoginService loginService = (LoginService) req.getServletContext().getAttribute(LOGIN_SERVICE);
        loginService.login(req, resp);
    }
}
