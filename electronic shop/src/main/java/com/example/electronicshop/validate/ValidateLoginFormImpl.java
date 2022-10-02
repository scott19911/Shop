package com.example.electronicshop.validate;

import com.example.electronicshop.users.LoginUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.electronicshop.dao.MySqlUserDao.EMAIL;
import static com.example.electronicshop.dao.MySqlUserDao.PASSWORD;

public class ValidateLoginFormImpl implements ValidateLoginForm {
    @Override
    public LoginUser readRequest(HttpServletRequest request) {
        LoginUser authorizationUser = new LoginUser();
        if (request.getParameter(EMAIL) != null) {
            authorizationUser.setEmail(request.getParameter(EMAIL));
        }
        if (request.getParameter(PASSWORD) != null) {
            authorizationUser.setPassword(request.getParameter(PASSWORD));
        }
        return authorizationUser;
    }

    @Override
    public Map<String, String> validate(LoginUser user) {
        Map<String, String> errorMap = new HashMap<>();
        String regexEmail = "^([_\\-0-9a-zA-Z]+)@([_\\-0-9a-zA-Z]+)\\.([a-zA-Z]){2,7}";
        if (user.getEmail() == null || !user.getEmail().matches(regexEmail)) {
            errorMap.put(EMAIL, "Email is missing or incorrect");
        }
        if (user.getPassword() == null || user.getPassword().length() < 3 || user.getPassword().length() > 10) {
            errorMap.put(PASSWORD, "Password is missing or his length not between 3 and 10");
        }
        return errorMap;
    }
}
