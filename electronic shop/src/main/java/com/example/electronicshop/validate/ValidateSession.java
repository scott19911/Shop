package com.example.electronicshop.validate;

import com.example.electronicshop.captchaStoreMode.CaptchaSessionStorage;
import com.example.electronicshop.registration.RegistrationDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * Reads and validates the data received from the session
 */
public class ValidateSession implements ValidatesRegistrationForm {
    /**
     * Setting registration form field names in constants
     */
    public static final String FIRST_NAME = "billing_first_name";
    public static final String LAST_NAME = "billing_last_name";
    public static final String EMAIL = "billing_email";
    public static final String PASSWORD = "account_password";
    public static final String CAPTCHA = "captcha";
    public static final String NOTIFICATION = "notifications";
    private Map<String, String> captchaMap;

    @Override
    public RegistrationDTO readRequest(HttpServletRequest request) {
        RegistrationDTO registrationBean = ValidatesRegistrationForm.super.readRequest(request);
        CaptchaSessionStorage captchaStore = new CaptchaSessionStorage();
        captchaMap = captchaStore.getCaptchaStore(request);
        return registrationBean;
    }

    @Override
    public Map<String, String> validate(RegistrationDTO bean) {
        Map<String, String> errorMap = ValidatesRegistrationForm.super.validate(bean);
        if (bean.getCaptcha() == null || !captchaMap.containsKey(bean.getCaptcha())) {
            errorMap.put(CAPTCHA, "Request time is off or captcha is wrong");
        }
        return errorMap;
    }

}
