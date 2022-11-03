package com.example.electronicshop.servletFilters.localization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Locale;

import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.LANGUAGE;

public class SessionLocalization implements LocalizationStrategy {
    @Override
    public boolean storage(HttpServletRequest request, HttpServletResponse response, LocalizationRequestWrapper requestWrapper) {
        HttpSession session = request.getSession();
        String lang = request.getParameter(LANGUAGE);
        if (lang != null && !lang.isBlank()) {
            session.setAttribute(LANGUAGE, lang);
        }
        if (session.getAttribute(LANGUAGE) != null) {
            requestWrapper.setLocale(Locale.forLanguageTag((String) session.getAttribute(LANGUAGE)));
            return true;
        }
        return false;
    }
}
