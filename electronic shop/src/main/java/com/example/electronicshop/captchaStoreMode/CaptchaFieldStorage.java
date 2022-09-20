package com.example.electronicshop.captchaStoreMode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_ID;
import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_LIFE_TIME;

/**
 * Implementation of captcha storage in hidden fields
 */
public class CaptchaFieldStorage implements CaptchaStorage {
    public static final Map<String, String> captchaMap = new HashMap<>();
    private static final Map<String, Long> captchaCreateTime = new HashMap<>();

    @Override
    public Map<String, String> getCaptchaStore(HttpServletRequest request) {
        return captchaMap;
    }

    @Override
    public void setCaptchaStore(HttpServletRequest request, HttpServletResponse response, String captcha) {
        String id = request.getParameter(CAPTCHA_ID);
        captchaMap.put(String.valueOf(id), captcha);
        captchaCreateTime.put(String.valueOf(id), System.currentTimeMillis());
        for (Entry<String, Long> time : captchaCreateTime.entrySet()) {
            if ((System.currentTimeMillis() - time.getValue()) > CAPTCHA_LIFE_TIME) {
                captchaMap.remove(time.getKey());
                captchaCreateTime.remove(time.getKey());
            }
        }
    }

}
