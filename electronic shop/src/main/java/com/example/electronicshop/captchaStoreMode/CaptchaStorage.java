package com.example.electronicshop.captchaStoreMode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * * Basic captcha storage interface
 */
public interface CaptchaStorage {
    /**
     * allows you to get a list of generated captchas
     *
     * @param request - query string
     * @return - captcha container
     */
    Map<String, String> getCaptchaStore(HttpServletRequest request);

    /**
     * Saves the generated captcha throughout its life cycle
     *
     * @param request  - query string
     * @param response - response to user
     * @param captcha  - generated number
     */
    void setCaptchaStore(HttpServletRequest request, HttpServletResponse response, String captcha);
}
