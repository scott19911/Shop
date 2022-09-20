package com.example.electronicshop.captchaStoreMode;

import static com.example.electronicshop.validate.ValidateFactory.COOKIE;
import static com.example.electronicshop.validate.ValidateFactory.FIELDS;

public class CaptchaStoreFactory {
    /**
     * allows you to get the necessary implementation of the captcha storage based on the application context
     *
     * @param context - application context
     * @return - captcha storage
     */
    public CaptchaStorage getCaptchaStore(String context) {
        switch (context) {
            case COOKIE:
                return new CaptchaCookieStorage();
            case FIELDS:
                return new CaptchaFieldStorage();
            default:
                return new CaptchaSessionStorage();
        }
    }
}
