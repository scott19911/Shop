package com.example.electronicshop.servletFilters.localization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class LocalizationRequestWrapper extends HttpServletRequestWrapper {
    private Locale locale;
    private Enumeration<Locale> locales;

    public LocalizationRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }
        return super.getLocale();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        if (locales != null) {
            return locales;
        }
        return super.getLocales();
    }

    public void setLocales(String[] availableLocales) {
        List<Locale> localeArrayList = new ArrayList<>();
        for (String str : availableLocales
        ) {
            localeArrayList.add(Locale.forLanguageTag(str));
        }
        locales = Collections.enumeration(localeArrayList);
    }
}
