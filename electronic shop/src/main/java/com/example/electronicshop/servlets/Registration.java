package com.example.electronicshop.servlets;

import com.example.electronicshop.registration.RegistrationDTO;
import com.example.electronicshop.service.RegistrationServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.electronicshop.constants.Pages.REGISTRATION_PAGE;
import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_ID;
import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_STORE_TYPE;
import static com.example.electronicshop.validate.ValidateFactory.FIELDS;

/**
 * Responsible for validation and registration of user data
 */
@WebServlet("/reg")
public class Registration extends HttpServlet {

    public static final String EMAIL = "billing_email";
    private int id = 0;
    public static final String DB_TYPE = "dbType";
    public static final String REGISTRATION_DTO = "com.example.electronicshop.registration.bean";
    public static final String REGISTRATION_ERROR = "com.example.electronicshop.registration.error";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        registrationService.registration(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        RegistrationDTO registrationBean = (RegistrationDTO) session
                .getAttribute(REGISTRATION_DTO);
        req.setAttribute(REGISTRATION_DTO, registrationBean);
        session.removeAttribute(REGISTRATION_DTO);
        Map<String, String> error = (Map<String, String>) session
                .getAttribute(REGISTRATION_ERROR);
        req.setAttribute(REGISTRATION_ERROR, error);
        session.removeAttribute(REGISTRATION_ERROR);
        if (getServletContext().getInitParameter(CAPTCHA_STORE_TYPE).equals(FIELDS)) {
            req.setAttribute(CAPTCHA_ID, id++);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(REGISTRATION_PAGE);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
