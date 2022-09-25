package com.example.electronicshop.validate;

import com.example.electronicshop.users.LoginUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface ValidateLoginForm {
    /**
     * read all form fields from request
     *
     * @param request - incoming request with registration form data
     * @return - read data as DTO class
     */
    LoginUser readRequest(HttpServletRequest request);

    /**
     * validation of entered data
     *
     * @param user - entered data
     * @return - error map with not valid data
     */
    Map<String, String> validate(LoginUser user);

}
