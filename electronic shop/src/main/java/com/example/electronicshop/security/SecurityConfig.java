package com.example.electronicshop.security;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for setting user access levels according to their role.
 */
public class SecurityConfig {
    private static Map<String, List<String>> mapConfig;

    public SecurityConfig(Map<String, List<String>> mapConfig) {
        SecurityConfig.mapConfig = mapConfig;
    }


    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}