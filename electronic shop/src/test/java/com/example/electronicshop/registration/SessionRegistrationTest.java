package com.example.electronicshop.registration;

import com.example.electronicshop.servlets.RegistrationServlets;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.electronicshop.validate.ValidateSession.CAPTCHA;
import static com.example.electronicshop.validate.ValidateSession.EMAIL;
import static com.example.electronicshop.validate.ValidateSession.FIRST_NAME;
import static com.example.electronicshop.validate.ValidateSession.LAST_NAME;
import static com.example.electronicshop.validate.ValidateSession.PASSWORD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class SessionRegistrationTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ServletContext servletContext = mock(ServletContext.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    public void shouldReturn_errorFirst_name_last_nameCaptcha_whenEnteredNotValidDataInThisFields() throws IOException {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("123456", "123456");
        when(request.getParameter(FIRST_NAME)).thenReturn("Name!");
        when(request.getParameter(LAST_NAME)).thenReturn("Last!");
        when(request.getParameter(EMAIL)).thenReturn("adm1@gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("adm");
        when(request.getParameter(CAPTCHA)).thenReturn("123451");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(anyString())).thenReturn("session");
        when(session.getAttribute(CAPTCHA)).thenReturn(captchaStore);
        Map<String, String> error = new HashMap<>();
        error.put(CAPTCHA, "Request time is off or captcha is wrong");
        error.put(FIRST_NAME, "First Name is missing or incorrect");
        error.put(LAST_NAME, "Last Name is missing or incorrect");

        RegistrationServlets registration = new RegistrationServlets();
        registration.doPost(request, response);

        verify(session, atLeastOnce()).setAttribute(eq("com.example.electronicshop.registration.bean"), any());
        verify(session, atLeastOnce()).setAttribute("com.example.electronicshop.registration.error", error);
        verify(response, atLeastOnce()).sendRedirect("/reg");
    }

    @Test
    public void shouldReturn_errorEmail_errorPassword_whenEnteredNotValidDataInThisFields() throws IOException {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("123456", "123456");
        when(request.getParameter(FIRST_NAME)).thenReturn("Name");
        when(request.getParameter(LAST_NAME)).thenReturn("Last");
        when(request.getParameter(EMAIL)).thenReturn("adm1gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("ad");
        when(request.getParameter(CAPTCHA)).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(anyString())).thenReturn("session");
        when(session.getAttribute(CAPTCHA)).thenReturn(captchaStore);
        Map<String, String> error = new HashMap<>();
        error.put(EMAIL, "Email is missing or incorrect");
        error.put(PASSWORD, "Password length must be great then 3 and lower 10 symbols");

        RegistrationServlets registration = new RegistrationServlets();
        registration.doPost(request, response);

        verify(session, atLeastOnce()).setAttribute(eq("com.example.electronicshop.registration.bean"), any());
        verify(session, atLeastOnce()).setAttribute("com.example.electronicshop.registration.error", error);
        verify(response, atLeastOnce()).sendRedirect("/reg");
    }

    @Test
    public void shouldReturn_errorAlreadyRegisterEmail_whenEnteredEmailAlreadyRegistered() throws IOException {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("123456", "123456");
        when(request.getParameter(FIRST_NAME)).thenReturn("Name");
        when(request.getParameter(LAST_NAME)).thenReturn("Last");
        when(request.getParameter(EMAIL)).thenReturn("adm@gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("ads");
        when(request.getParameter(CAPTCHA)).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(anyString())).thenReturn("session");
        when(session.getAttribute(CAPTCHA)).thenReturn(captchaStore);
        Map<String, String> error = new HashMap<>();
        error.put(EMAIL, "Already register");

        RegistrationServlets registration = new RegistrationServlets();
        registration.doPost(request, response);

        verify(session, atLeastOnce()).setAttribute(eq("com.example.electronicshop.registration.bean"), any());
        verify(session, atLeastOnce()).setAttribute("com.example.electronicshop.registration.error", error);
        verify(response, atLeastOnce()).sendRedirect("/reg");
    }

    @Test
    public void shouldSendRedirect_toShopPage_whenEnteredValidData() throws IOException {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("123456", "123456");
        when(request.getParameter(FIRST_NAME)).thenReturn("Name");
        when(request.getParameter(LAST_NAME)).thenReturn("Last");
        when(request.getParameter(EMAIL)).thenReturn("admw@gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("ads");
        when(request.getParameter(CAPTCHA)).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(anyString())).thenReturn("session");
        when(session.getAttribute(CAPTCHA)).thenReturn(captchaStore);

        RegistrationServlets registration = new RegistrationServlets();
        registration.doPost(request, response);

        verify(response, atLeastOnce()).sendRedirect("/shop.html");
    }
}