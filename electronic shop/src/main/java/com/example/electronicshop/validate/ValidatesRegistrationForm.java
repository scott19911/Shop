package com.example.electronicshop.validate;

import com.example.electronicshop.registration.RegistrationDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.electronicshop.validate.ValidateSession.CAPTCHA;
import static com.example.electronicshop.validate.ValidateSession.EMAIL;
import static com.example.electronicshop.validate.ValidateSession.FIRST_NAME;
import static com.example.electronicshop.validate.ValidateSession.LAST_NAME;
import static com.example.electronicshop.validate.ValidateSession.NOTIFICATION;
import static com.example.electronicshop.validate.ValidateSession.PASSWORD;

/**
 * Allows you to read all form fields and validate them
 */
public interface ValidatesRegistrationForm {
    /**
     * read all form fields from request
     *
     * @param request - incoming request with registration form data
     * @return - read data as bean class
     */
    default RegistrationDTO readRequest(HttpServletRequest request) {
        RegistrationDTO registrationBean = new RegistrationDTO();
        registrationBean.setFirstName(request.getParameter(FIRST_NAME));
        registrationBean.setLastName(request.getParameter(LAST_NAME));
        registrationBean.setCaptcha(request.getParameter(CAPTCHA));
        registrationBean.setEmail(request.getParameter(EMAIL));
        registrationBean.setPassword(request.getParameter(PASSWORD));
        registrationBean.setNotification(Boolean.parseBoolean(request.getParameter(NOTIFICATION)));
        return registrationBean;
    }

    /**
     * validation of entered data
     *
     * @param bean - entered data
     * @return - error map with not valid data
     */
    default Map<String, String> validate(RegistrationDTO bean) {
        Map<String, String> errorMap = new HashMap<>();
        String regexName = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:]{2,}";
        String regexEmail = "^([_\\-0-9a-zA-Z]+)@([_\\-0-9a-zA-Z]+)\\.([a-zA-Z]){2,7}";
        if (bean.getFirstName() == null || checkRegex(bean.getFirstName(), regexName)) {
            errorMap.put(FIRST_NAME, "First Name is missing or incorrect");
        }
        if (bean.getLastName() == null || checkRegex(bean.getLastName(), regexName)) {
            errorMap.put(LAST_NAME, "Last Name is missing or incorrect");
        }
        if (bean.getEmail() == null || checkRegex(bean.getEmail(), regexEmail)) {
            errorMap.put(EMAIL, "Email is missing or incorrect");
        }
        if (bean.getPassword() == null || bean.getPassword().length() < 3 || bean.getPassword().length() > 10) {
            errorMap.put(PASSWORD, "Password length must be great then 3 and lower 10 symbols");
        }
        if (bean.getCaptcha() == null || checkRegex(bean.getCaptcha(), "^[0-9]{6}")) {
            errorMap.put(CAPTCHA, "Wrong captcha format");
        }
        return errorMap;
    }

    /**
     * Checking data for emptiness and matching with a regular expression
     *
     * @param parameter - Checking data
     * @param regex     - regular expression
     * @return - true if not empty and matching with a regular expression
     */
    default boolean checkRegex(String parameter, String regex) {
        return parameter.isEmpty() || !parameter.matches(regex);
    }
}
