package com.example.electronicshop.servletFilters.localization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.AVAILABLE_LOCALES;
import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.COOKIES_LIFE;
import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.LANGUAGE;
import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.LOCALE_STORAGE;
import static com.example.electronicshop.servletFilters.localization.LocalizationFilter.SET_LOCALE;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LocalizationFilterTest {
    LocalizationFilter localizationFilter = new LocalizationFilter();
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    FilterChain filterChain = mock(FilterChain.class);
    ServletContext servletContext = mock(ServletContext.class);
    List<Locale> localeList;
    Enumeration<Locale> localeEnumeration;

    @BeforeEach
    void init() {
        localeList = new ArrayList<>();
        localeList.add(Locale.forLanguageTag("pl"));
        localeList.add(Locale.forLanguageTag("jp"));
        localeEnumeration = Collections.enumeration(localeList);
    }

    @Test
    public void shouldSetEnLocale_whenUserLocaleUnsupported() throws ServletException, IOException {
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(LOCALE_STORAGE)).thenReturn(null);
        when(servletContext.getInitParameter(COOKIES_LIFE)).thenReturn("200");
        when(servletContext.getInitParameter(AVAILABLE_LOCALES)).thenReturn("uk;en");
        when(request.getLocales()).thenReturn(localeEnumeration);
        when(request.getSession()).thenReturn(session);
        localizationFilter.doFilter(request, response, filterChain);
        verify(session, atLeastOnce()).setAttribute(SET_LOCALE, Locale.forLanguageTag("en"));
    }

    @Test
    public void shouldSetUKLocale_whenUserLocalesSupportedUk() throws ServletException, IOException {
        localeList.add(Locale.forLanguageTag("uk"));
        Enumeration<Locale> localeEnumeration = Collections.enumeration(localeList);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(LOCALE_STORAGE)).thenReturn(null);
        when(servletContext.getInitParameter(COOKIES_LIFE)).thenReturn("200");
        when(servletContext.getInitParameter(AVAILABLE_LOCALES)).thenReturn("uk;en");
        when(request.getLocales()).thenReturn(localeEnumeration);
        when(request.getSession()).thenReturn(session);
        localizationFilter.doFilter(request, response, filterChain);
        verify(session, atLeastOnce()).setAttribute(SET_LOCALE, Locale.forLanguageTag("uk"));
    }

    @Test
    public void shouldSetUKLocale_whenUserSetUkLocale() throws ServletException, IOException {
        Enumeration<Locale> localeEnumeration = Collections.enumeration(localeList);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getParameter(LANGUAGE)).thenReturn("uk");
        when(servletContext.getInitParameter(LOCALE_STORAGE)).thenReturn(null);
        when(servletContext.getInitParameter(COOKIES_LIFE)).thenReturn("200");
        when(servletContext.getInitParameter(AVAILABLE_LOCALES)).thenReturn("uk;en");
        when(request.getLocales()).thenReturn(localeEnumeration);
        when(request.getSession()).thenReturn(session);
        localizationFilter.doFilter(request, response, filterChain);
        verify(request, atLeastOnce()).getCookies();
        verify(session, atLeastOnce()).setAttribute(SET_LOCALE, Locale.forLanguageTag("uk"));
    }

    @Test
    public void shouldSetEnLocale_whenUserSetEnLocale() throws ServletException, IOException {
        Enumeration<Locale> localeEnumeration = Collections.enumeration(localeList);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getParameter(LANGUAGE)).thenReturn("en");
        when(servletContext.getInitParameter(LOCALE_STORAGE)).thenReturn(null);
        when(servletContext.getInitParameter(COOKIES_LIFE)).thenReturn("200");
        when(servletContext.getInitParameter(AVAILABLE_LOCALES)).thenReturn("uk;en");
        when(servletContext.getInitParameter(LOCALE_STORAGE)).thenReturn("session");
        when(request.getLocales()).thenReturn(localeEnumeration);
        when(request.getSession()).thenReturn(session);
        localizationFilter.doFilter(request, response, filterChain);
        verify(session, atLeastOnce()).setAttribute(LANGUAGE, "en");
        verify(session, atLeastOnce()).setAttribute(SET_LOCALE, Locale.forLanguageTag("en"));
    }
}