package com.example.electronicshop.service;

import com.example.electronicshop.registration.RegistrationDTO;
import com.example.electronicshop.users.SpecificUser;
import com.example.electronicshop.users.User;
import com.example.electronicshop.validate.ValidateFactory;
import com.example.electronicshop.validate.ValidatesRegistrationForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;


import static com.example.electronicshop.constants.ServletsName.REGISTRATION_SERVLET;
import static com.example.electronicshop.constants.ServletsName.SHOP_SERVLET;
import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_STORE_TYPE;
import static com.example.electronicshop.service.UploadAvatar.SPECIFIC_USER;
import static com.example.electronicshop.servlets.RegistrationServlets.DB_TYPE;
import static com.example.electronicshop.servlets.RegistrationServlets.EMAIL;
import static com.example.electronicshop.servlets.RegistrationServlets.REGISTRATION_DTO;
import static com.example.electronicshop.servlets.RegistrationServlets.REGISTRATION_ERROR;


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
                SpecificUser specificUser;
                UserServiceFactory userServiceFactory = new UserServiceFactory();
                UserService userService = userServiceFactory.getService
                        (session.getServletContext().getInitParameter(DB_TYPE));
                if (session.getAttribute(SPECIFIC_USER) == null) {
                    specificUser = new SpecificUser();
                } else {
                    specificUser = (SpecificUser) session.getAttribute(SPECIFIC_USER);
                    user.setAvatarUdl(specificUser.getAvatarUrl());
                }
                int userId = userService.createUser(user);
                if (userId > 0) {
                    specificUser.setFirstName(user.getFirstName());
                    specificUser.setUserId(userId);
                    session.setAttribute(SPECIFIC_USER, specificUser);
                } else {
                    error.put(EMAIL, "Already register email");
                }
            } catch (RuntimeException exception) {
                error.put(EMAIL, exception.getMessage());
            }
        }
        if (error.isEmpty()) {
            resp.sendRedirect(SHOP_SERVLET);
        } else {
            registrationBean.setPassword("");
            registrationBean.setCaptcha("");
            session.setAttribute(REGISTRATION_DTO, registrationBean);
            session.setAttribute(REGISTRATION_ERROR, error);
            resp.sendRedirect(REGISTRATION_SERVLET);
        }
    }
}
