package com.example.electronicshop.security;

import com.example.electronicshop.users.SpecificUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.electronicshop.security.SecurityFilter.ERROR_MESSAGE;
import static com.example.electronicshop.service.impl.UploadAvatar.SPECIFIC_USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SecurityFilterTest {
    @Test
    void shouldReturnToShopPage_whenUserNotLoginAskSecurityPageFromShopPage() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        MockedStatic<UrlPatternUtils> utilities = Mockito.mockStatic(UrlPatternUtils.class);
        String shopServlet = "/shop";
        utilities.when(() -> UrlPatternUtils.getUrlPattern(request)).thenReturn("/order");
        List<String> listPermission = new ArrayList<>();
        listPermission.add("/order");
        Map<String, List<String>> mapConfig = new HashMap<>();
        mapConfig.put("user", listPermission);
        SecurityConfig securityConfig = new SecurityConfig(mapConfig);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getHeader("referer")).thenReturn(shopServlet);
        when(request.getRequestURI()).thenReturn("/order");
        when(request.getSession()).thenReturn(session);
        SecurityFilter securityFilter = new SecurityFilter();
        FilterChain filterChain = mock(FilterChain.class);
        securityFilter.doFilter(request, response, filterChain);
        verify(session,atLeastOnce()).setAttribute(ERROR_MESSAGE,"Sorry you need Sign in");
        verify(response, atLeastOnce()).sendRedirect(shopServlet);
        utilities.close();
    }

    @Test
    void shouldGoToOrderServlet_whenUserHasPermissionDoChain() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        MockedStatic<UrlPatternUtils> utilities = Mockito.mockStatic(UrlPatternUtils.class);
        String shopServlet = "/shop";
        utilities.when(() -> UrlPatternUtils.getUrlPattern(any())).thenReturn("/order").thenReturn("/order");
        List<String> listPermission = new ArrayList<>();
        listPermission.add("/order");
        Map<String, List<String>> mapConfig = new HashMap<>();
        mapConfig.put("user", listPermission);
        SpecificUser specificUser = new SpecificUser();
        specificUser.setUserRole("user");
        SecurityConfig securityConfig = new SecurityConfig(mapConfig);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getHeader("referer")).thenReturn(shopServlet);
        when(request.getRequestURI()).thenReturn("/order");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(SPECIFIC_USER)).thenReturn(specificUser);
        SecurityFilter securityFilter = new SecurityFilter();
        FilterChain filterChain = mock(FilterChain.class);
        securityFilter.doFilter(request, response, filterChain);
        verify(filterChain, atLeastOnce()).doFilter(any(),eq(response));
        utilities.close();
    }
    @Test
    void shouldGoToErrorPage_whenUserDoesNotHasPermission() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        MockedStatic<UrlPatternUtils> utilities = Mockito.mockStatic(UrlPatternUtils.class);
        String shopServlet = "/shop";
        String orderProcessingServlet ="/confirm";
        utilities.when(() -> UrlPatternUtils.getUrlPattern(any())).thenReturn(orderProcessingServlet).thenReturn(orderProcessingServlet);
        List<String> listPermission = new ArrayList<>();
        List<String> listPermissionAdmin = new ArrayList<>();
        listPermission.add("/order");
        listPermissionAdmin.add(orderProcessingServlet);
        Map<String, List<String>> mapConfig = new HashMap<>();
        mapConfig.put("user", listPermission);
        mapConfig.put("admin", listPermissionAdmin);
        SpecificUser specificUser = new SpecificUser();
        specificUser.setUserRole("user");
        SecurityConfig securityConfig = new SecurityConfig(mapConfig);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getHeader("referer")).thenReturn(shopServlet);
        when(request.getRequestURI()).thenReturn(orderProcessingServlet);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(SPECIFIC_USER)).thenReturn(specificUser);
        when(request.getRequestDispatcher("/ErrorPage.jsp")).thenReturn(requestDispatcher);
        SecurityFilter securityFilter = new SecurityFilter();
        FilterChain filterChain = mock(FilterChain.class);
        securityFilter.doFilter(request, response, filterChain);
        verify(request,atLeastOnce()).setAttribute(ERROR_MESSAGE, "Sorry you can't go there");
        verify(requestDispatcher, atLeastOnce()).forward(request,response);
        utilities.close();
    }
}