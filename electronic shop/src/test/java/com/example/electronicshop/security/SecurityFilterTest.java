package com.example.electronicshop.security;

import com.example.electronicshop.users.SpecificUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.electronicshop.constants.ServletsName.ORDER_SERVLET;
import static com.example.electronicshop.constants.ServletsName.PRODUCT_LIST_SERVLET;
import static com.example.electronicshop.security.SecurityFilter.ERROR_MESSAGE;
import static com.example.electronicshop.service.impl.UploadAvatar.SPECIFIC_USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SecurityFilterTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private MockedStatic<UrlPatternUtils> utilities;
    private FilterChain filterChain;
    private SecurityFilter securityFilter;
    private List<String> listPermission;
    private Map<String, List<String>> mapConfig;

    @BeforeEach
    void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);
        session = mock(HttpSession.class);
        utilities = Mockito.mockStatic(UrlPatternUtils.class);
        filterChain = mock(FilterChain.class);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getSession()).thenReturn(session);
        securityFilter = new SecurityFilter();
        listPermission = new ArrayList<>();
        mapConfig = new HashMap<>();
    }

    @Test
    void shouldReturnToShopPage_whenUserNotLoginAskSecurityPageFromShopPage() throws ServletException, IOException {
        utilities.when(() -> UrlPatternUtils.getUrlPattern(request)).thenReturn(ORDER_SERVLET);
        listPermission.add(ORDER_SERVLET);
        mapConfig.put("user", listPermission);
        SecurityConfig.setMapConfig(mapConfig);
        when(request.getHeader("referer")).thenReturn(PRODUCT_LIST_SERVLET);
        when(request.getRequestURI()).thenReturn(ORDER_SERVLET);
        securityFilter.doFilter(request, response, filterChain);
        verify(session, atLeastOnce()).setAttribute(ERROR_MESSAGE, "Sorry you need Sign in");
        verify(response, atLeastOnce()).sendRedirect(PRODUCT_LIST_SERVLET);
        utilities.close();
    }

    @Test
    void shouldGoToOrderServlet_whenUserHasPermissionDoChain() throws ServletException, IOException {
        utilities.when(() -> UrlPatternUtils.getUrlPattern(any())).thenReturn(ORDER_SERVLET).thenReturn(ORDER_SERVLET);
        listPermission.add(ORDER_SERVLET);
        mapConfig.put("user", listPermission);
        SpecificUser specificUser = new SpecificUser();
        specificUser.setUserRole("user");
        SecurityConfig.setMapConfig(mapConfig);
        when(request.getHeader("referer")).thenReturn(PRODUCT_LIST_SERVLET);
        when(request.getRequestURI()).thenReturn(ORDER_SERVLET);
        when(session.getAttribute(SPECIFIC_USER)).thenReturn(specificUser);
        securityFilter.doFilter(request, response, filterChain);
        verify(filterChain, atLeastOnce()).doFilter(any(), eq(response));
        utilities.close();
    }
    @Test
    void shouldGoToErrorPage_whenUserDoesNotHasPermission() throws ServletException, IOException {
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        String orderProcessingServlet = "/confirm";
        utilities.when(() -> UrlPatternUtils.getUrlPattern(any())).thenReturn(orderProcessingServlet)
                .thenReturn(orderProcessingServlet);
        List<String> listPermissionAdmin = new ArrayList<>();
        listPermission.add("/order");
        listPermissionAdmin.add(orderProcessingServlet);
        mapConfig.put("user", listPermission);
        mapConfig.put("admin", listPermissionAdmin);
        SpecificUser specificUser = new SpecificUser();
        specificUser.setUserRole("user");
        SecurityConfig.setMapConfig(mapConfig);
        when(request.getHeader("referer")).thenReturn(PRODUCT_LIST_SERVLET);
        when(request.getRequestURI()).thenReturn(orderProcessingServlet);
        when(session.getAttribute(SPECIFIC_USER)).thenReturn(specificUser);
        when(request.getRequestDispatcher("/ErrorPage.jsp")).thenReturn(requestDispatcher);
        securityFilter.doFilter(request, response, filterChain);
        verify(request, atLeastOnce()).setAttribute(ERROR_MESSAGE, "Sorry you can't go there");
        verify(requestDispatcher, atLeastOnce()).forward(any(), eq(response));
        utilities.close();
    }
}