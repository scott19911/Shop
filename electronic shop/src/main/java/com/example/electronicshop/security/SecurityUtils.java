package com.example.electronicshop.security;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


public class SecurityUtils {
    /**
     *
     * checks the query string for security content
     * @param request - query string
     * @return - true if the query string for security content
     */
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> roles = SecurityConfig.getAllAppRoles();
        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
    /**
     *checks the user role has permission for security content
     * @param request - query string
     * @return - true if the user role has permission
     */
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> allRoles = SecurityConfig.getAllAppRoles();
        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}