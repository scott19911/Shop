package com.example.electronicshop.service;

import com.example.electronicshop.dao.UserDaoFactory;
import com.example.electronicshop.registration.RegistrationDTO;
import com.example.electronicshop.users.User;
import com.example.electronicshop.validate.ValidateFactory;
import com.example.electronicshop.validate.ValidatesRegistrationForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_STORE_TYPE;
import static com.example.electronicshop.registration.Registration.DB_TYPE;
import static com.example.electronicshop.registration.Registration.EMAIL;
import static com.example.electronicshop.registration.Registration.REGISTRATION_DTO;
import static com.example.electronicshop.registration.Registration.REGISTRATION_ERROR;

/**
 * Responsible for registering a new user
 */
public class RegistrationServiceImpl implements RegistrationService {
    @Override
    public void registration(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user;
        ValidateFactory validateFactory = new ValidateFactory();
        ValidatesRegistrationForm validates = validateFactory.createValidates(session.getServletContext().getInitParameter(CAPTCHA_STORE_TYPE));
        RegistrationDTO registrationBean = validates.readRequest(req);
        Map<String, String> error = validates.validate(registrationBean);
        if (error.isEmpty()) {
            user = registrationBean.getUser();
            try {
                UserDaoFactory userDaoFactory = new UserDaoFactory();
                UserServiceMapStore userService = new UserServiceMapStore(
                        userDaoFactory.getUserDao(session.getServletContext().getInitParameter(DB_TYPE)));
                userService.createUser(user);
            } catch (RuntimeException exception) {
                error.put(EMAIL, exception.getMessage());
            }
        }
        if (error.isEmpty()) {
            resp.sendRedirect("/shop.html");
        } else {
            registrationBean.setPassword("");
            registrationBean.setCaptcha("");
            session.setAttribute(REGISTRATION_DTO, registrationBean);
            session.setAttribute(REGISTRATION_ERROR, error);
            resp.sendRedirect("/reg");
        }
    }
}
