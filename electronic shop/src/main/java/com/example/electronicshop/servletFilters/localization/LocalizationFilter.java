package com.example.electronicshop.servletFilters.localization;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import static com.example.electronicshop.utils.ContextListener.STRATEGY_LOCALIZATION;

public class LocalizationFilter implements Filter {
    public static final String LANGUAGE = "lang";
    public static final String SET_LOCALE = "jakarta.servlet.jsp.jstl.fmt.fallbackLocale.request";
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
        LocalizationStrategy localizationStrategy = (LocalizationStrategy)
                request.getServletContext().getAttribute(STRATEGY_LOCALIZATION);
        boolean userSetLocale = localizationStrategy.storage(request, response, requestWrapper);
        if (!userSetLocale) {
            String newLang = getAvailableLanguage(request, requestWrapper);
            requestWrapper.setLocale(Locale.forLanguageTag(newLang));
        }
        request.setAttribute(SET_LOCALE, requestWrapper.getLocale());
        filterChain.doFilter(requestWrapper, response);
    }

    /**
     * Returns the most preferred string representation of the locale, based on the available locales
     *
     * @param request        - input request
     * @param requestWrapper - wrapped response
     * @return - locale
     */
    private String getAvailableLanguage(HttpServletRequest request, LocalizationRequestWrapper requestWrapper) {
        String[] availableLocales = request.getServletContext().getInitParameter(AVAILABLE_LOCALES).toLowerCase().split(";");
        Iterator<Locale> localeIterator = request.getLocales().asIterator();
        requestWrapper.setLocales(availableLocales);
        while (localeIterator.hasNext()) {
            String locals = localeIterator.next().toString();
            String findLocal = Arrays.stream(availableLocales).filter(locales -> locales.equalsIgnoreCase(locals))
                    .findFirst().orElse(null);
            if (findLocal != null) {
                return findLocal;
            }
        }
        return "";
    }
}
