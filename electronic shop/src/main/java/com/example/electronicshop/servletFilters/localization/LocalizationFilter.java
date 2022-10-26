package com.example.electronicshop.servletFilters.localization;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

public class LocalizationFilter implements Filter {
    public static final String LANGUAGE = "lang";
    public static final String SET_LOCALE = "jakarta.servlet.jsp.jstl.fmt.locale.session";
    public static final String DEFAULT_LOCALE = "en";
    public static final String AVAILABLE_LOCALES = "availableLocale";
    public static final String COOKIES_LIFE = "cookiesLives";
    public static final String LOCALE_STORAGE = "localeStorage";
    public static final String SESSION_LOCALE_STORAGE = "session";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LocalizationRequestWrapper requestWrapper = new LocalizationRequestWrapper(request);
        String localeStorage = request.getServletContext().getInitParameter(LOCALE_STORAGE);
        boolean userSetLocale;
        if (SESSION_LOCALE_STORAGE.equals(localeStorage)) {
            userSetLocale = sessionStorage(request, requestWrapper);
        } else {
            userSetLocale = cookiesStorage(request, response, requestWrapper);
        }
        if (!userSetLocale) {
            String newLang = getAvailableLanguage(request, requestWrapper);
            requestWrapper.setLocale(Locale.forLanguageTag(newLang));
        }
        requestWrapper.getSession().setAttribute(SET_LOCALE, requestWrapper.getLocale());
        filterChain.doFilter(requestWrapper, response);
    }

    /**
     * When storing the locale in cookies, reads or set the locale and extends the life of the cookie
     *
     * @param request        - input request
     * @param response       - output response
     * @param requestWrapper - wrapped response
     * @return - true if we use cookies
     */
    private boolean cookiesStorage(HttpServletRequest request, HttpServletResponse response, LocalizationRequestWrapper requestWrapper) {
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
     * When storing the locale in session, reads or set the locale
     *
     * @param request        - input request
     * @param requestWrapper - wrapped response
     * @return - true if we use session to store locale
     */
    private boolean sessionStorage(HttpServletRequest request, LocalizationRequestWrapper requestWrapper) {
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

    /**
     * Returns the most preferred string representation of the locale, based on the available locales
     *
     * @param request        - input request
     * @param requestWrapper - wrapped response
     * @return - locale
     */
    private String getAvailableLanguage(HttpServletRequest request, LocalizationRequestWrapper requestWrapper) {
        String[] availableLocales = request.getServletContext().getInitParameter(AVAILABLE_LOCALES).split(";");
        Iterator<Locale> localeIterator = request.getLocales().asIterator();
        requestWrapper.setLocales(availableLocales);
        while (localeIterator.hasNext()) {
            String locals = localeIterator.next().toString();
            for (String local : availableLocales
            ) {
                if (locals.equalsIgnoreCase(local)) {
                    return local;
                }
            }
        }
        return DEFAULT_LOCALE;
    }
}
