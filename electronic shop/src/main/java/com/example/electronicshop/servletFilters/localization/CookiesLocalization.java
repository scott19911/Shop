package com.example.electronicshop.servletFilters.localization;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Locale;

import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.COOKIES_LIFE;
import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.LANGUAGE;

public class CookiesLocalization implements LocalizationStrategy {
    @Override
    public boolean storage(HttpServletRequest request, HttpServletResponse response, LocalizationRequestWrapper requestWrapper) {
        int cookiesLife = Integer.parseInt(request.getServletContext().getInitParameter(COOKIES_LIFE));
        Cookie cookie = getCookie(request);
        String lang = request.getParameter(LANGUAGE);
        if (lang != null && !lang.isBlank()) {
            cookie = new Cookie(LANGUAGE, lang);
        }
        if (cookie != null) {
            cookie.setMaxAge(cookiesLife);
            response.addCookie(cookie);
            requestWrapper.setLocale(Locale.forLanguageTag(cookie.getValue()));
            return true;
        }
        return false;
    }
    /**
     * Extract cookies from request
     *
     * @param request - input request
     * @return - cookie if exist else null
     */
    private Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (LANGUAGE.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}
