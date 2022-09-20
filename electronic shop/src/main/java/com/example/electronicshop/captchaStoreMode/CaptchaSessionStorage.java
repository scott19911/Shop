package com.example.electronicshop.captchaStoreMode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_LIFE_TIME;

/**
 * Implementation of captcha storage in the session
 */
public class CaptchaSessionStorage implements CaptchaStorage {
    private static final String CAPTCHA = "captcha";
    private final Map<String, Long> captchaCreateTime = new HashMap<>();

    @Override
    public Map<String, String> getCaptchaStore(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Map<String, String>) session.getAttribute(CAPTCHA);
    }

    @Override
    public void setCaptchaStore(HttpServletRequest request, HttpServletResponse response, String captcha) {
        HttpSession session = request.getSession();
        Map<String, String> captchaList = new HashMap<>();
        captchaCreateTime.put(captcha, System.currentTimeMillis());
        if (getCaptchaStore(request) != null && getCaptchaStore(request).size() > 0) {
            captchaList = getCaptchaStore(request);
            captchaList.put(captcha, captcha);
        }
        for (Entry<String, Long> time : captchaCreateTime.entrySet()) {
            if ((System.currentTimeMillis() - time.getValue()) > CAPTCHA_LIFE_TIME) {
                captchaList.remove(time.getKey());
                captchaCreateTime.remove(time.getKey());
            }
        }
        captchaList.put(captcha, captcha);
        session.setAttribute(CAPTCHA, captchaList);
    }
}
