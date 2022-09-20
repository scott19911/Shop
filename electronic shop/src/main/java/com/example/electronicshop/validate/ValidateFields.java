package com.example.electronicshop.validate;

import com.example.electronicshop.captchaStoreMode.CaptchaFieldStorage;
import com.example.electronicshop.registration.RegistrationDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_ID;


/**
 * Reads and validates data received from hidden fields
 */
public class ValidateFields implements ValidatesRegistrationForm {
    private static final String CAPTCHA = "captcha";
    /**
     * captcha storage class with hidden id field
     */
    public CaptchaFieldStorage captchaFieldStore = new CaptchaFieldStorage();
    private Map<String, String> captchaMap;
    private String captchaId;

    @Override
    public RegistrationDTO readRequest(HttpServletRequest request) {
        RegistrationDTO registrationBean = ValidatesRegistrationForm.super.readRequest(request);
        captchaId = request.getParameter(CAPTCHA_ID);
        captchaMap = captchaFieldStore.getCaptchaStore(request);
        return registrationBean;
    }

    @Override
    public Map<String, String> validate(RegistrationDTO bean) {
        Map<String, String> errorMap = ValidatesRegistrationForm.super.validate(bean);
        if (captchaId == null || captchaMap.get(captchaId) == null) {
            errorMap.put(CAPTCHA, "Request time is off ");
        } else if (!captchaMap.get(captchaId).equals(bean.getCaptcha())) {
            errorMap.put(CAPTCHA, "Captcha doesn't match");
        }
        return errorMap;
    }

}
