package com.example.electronicshop.validate;

import com.example.electronicshop.captchaStoreMode.CaptchaCookieStorage;
import com.example.electronicshop.registration.RegistrationDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_ID;


/**
 * Reads and validates data received from cookies
 */
public class ValidateCookie implements ValidatesRegistrationForm {
    private static final String CAPTCHA = "captcha";
    /**
     * captcha storage class with cookie
     */
    private CaptchaCookieStorage cookieStorage = new CaptchaCookieStorage();
    private Cookie cookie;
    private Map<String, String> captchaMap;
    private String captchaId;

    @Override
    public RegistrationDTO readRequest(HttpServletRequest request) {
        RegistrationDTO registrationBean = ValidatesRegistrationForm.super.readRequest(request);
        Cookie[] cookies = request.getCookies();
        cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (CAPTCHA_ID.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if (cookie != null) {
            captchaId = cookie.getValue();
        }
        captchaMap = cookieStorage.getCaptchaStore(request);
        return registrationBean;
    }

    @Override
    public Map<String, String> validate(RegistrationDTO bean) {
        Map<String, String> errorMap = ValidatesRegistrationForm.super.validate(bean);
        if (cookie == null) {
            errorMap.put(CAPTCHA, "Request time is off");
        } else if (!captchaMap.get(captchaId).equals(bean.getCaptcha())) {
            errorMap.put(CAPTCHA, "captcha doesn't match");
        }
        return errorMap;
    }

}
