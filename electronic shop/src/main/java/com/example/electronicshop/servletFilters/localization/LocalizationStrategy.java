package com.example.electronicshop.servletFilters.localization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LocalizationStrategy {
    /**
     * When storing the locale in storage, reads or set the locale
     *
     * @param request        - input request
     * @param response       - output response
     * @param requestWrapper - wrapped response
     * @return - true if we set storage
     */
    boolean storage(HttpServletRequest request, HttpServletResponse response, LocalizationRequestWrapper requestWrapper);
}
