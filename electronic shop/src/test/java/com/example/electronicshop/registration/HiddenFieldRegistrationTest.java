package com.example.electronicshop.registration;

import com.example.electronicshop.captchaStoreMode.CaptchaFieldStorage;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_ID;
import static com.example.electronicshop.registration.DrawCaptcha.CAPTCHA_STORE_TYPE;
import static com.example.electronicshop.registration.Registration.DB_TYPE;
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

public class HiddenFieldRegistrationTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ServletContext servletContext = mock(ServletContext.class);
    HttpSession session = mock(HttpSession.class);

    @BeforeEach
    void init() {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("0", "123456");
        CaptchaFieldStorage.captchaMap.putAll(captchaStore);
    }

    @Test
    public void shouldReturn_errorFirst_name_last_nameCaptcha_whenEnteredNotValidDataInThisFields() throws IOException {
        when(request.getParameter(FIRST_NAME)).thenReturn("Name!");
        when(request.getParameter(LAST_NAME)).thenReturn("Last!");
        when(request.getParameter(EMAIL)).thenReturn("adm1@gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("adm");
        when(request.getParameter(CAPTCHA)).thenReturn("123451");
        when(request.getParameter(CAPTCHA_ID)).thenReturn("0");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(anyString())).thenReturn("field");

        Map<String, String> error = new HashMap<>();
        error.put(CAPTCHA, "Captcha doesn't match");
        error.put(FIRST_NAME, "First Name is missing or incorrect");
        error.put(LAST_NAME, "Last Name is missing or incorrect");

        Registration registration = new Registration();
        registration.doPost(request, response);

        verify(session, atLeastOnce()).setAttribute(eq("com.example.electronicshop.registration.bean"), any());
        verify(session, atLeastOnce()).setAttribute("com.example.electronicshop.registration.error", error);
        verify(response, atLeastOnce()).sendRedirect("/reg");
    }

    @Test
    public void shouldReturn_errorEmail_errorPassword_whenEnteredNotValidDataInThisFields() throws IOException {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("0", "123456");
        when(request.getParameter(FIRST_NAME)).thenReturn("Name");
        when(request.getParameter(LAST_NAME)).thenReturn("Last");
        when(request.getParameter(EMAIL)).thenReturn("adm1gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("ad");
        when(request.getParameter(CAPTCHA)).thenReturn("123456");
        when(request.getParameter(CAPTCHA_ID)).thenReturn("0");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(anyString())).thenReturn("field");
        when(session.getAttribute(CAPTCHA)).thenReturn(captchaStore);
        Map<String, String> error = new HashMap<>();
        error.put(EMAIL, "Email is missing or incorrect");
        error.put(PASSWORD, "Password length must be great then 3 and lower 10 symbols");

        Registration registration = new Registration();
        registration.doPost(request, response);

        verify(session, atLeastOnce()).setAttribute(eq("com.example.electronicshop.registration.bean"), any());
        verify(session, atLeastOnce()).setAttribute("com.example.electronicshop.registration.error", error);
        verify(response, atLeastOnce()).sendRedirect("/reg");
    }

    @Test
    public void shouldReturn_errorAlreadyRegisterEmail_whenEnteredEmailAlreadyRegistered() throws IOException {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("0", "123456");
        when(request.getParameter(FIRST_NAME)).thenReturn("Name");
        when(request.getParameter(LAST_NAME)).thenReturn("Last");
        when(request.getParameter(EMAIL)).thenReturn("adm@gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("ads");
        when(request.getParameter(CAPTCHA)).thenReturn("123456");
        when(request.getParameter(CAPTCHA_ID)).thenReturn("0");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(CAPTCHA_STORE_TYPE)).thenReturn("field");
        when(servletContext.getInitParameter(DB_TYPE)).thenReturn("map");
        when(session.getAttribute(CAPTCHA)).thenReturn(captchaStore);
        Map<String, String> error = new HashMap<>();
        error.put(EMAIL, "Already register");

        Registration registration = new Registration();
        registration.doPost(request, response);

        verify(session, atLeastOnce()).setAttribute(eq("com.example.electronicshop.registration.bean"), any());
        verify(session, atLeastOnce()).setAttribute("com.example.electronicshop.registration.error", error);
        verify(response, atLeastOnce()).sendRedirect("/reg");
    }

    @Test
    public void shouldSendRedirect_toShopPage_whenEnteredValidData() throws IOException {
        Map<String, String> captchaStore = new HashMap<>();
        captchaStore.put("0", "123456");
        when(request.getParameter(FIRST_NAME)).thenReturn("Name");
        when(request.getParameter(LAST_NAME)).thenReturn("Last");
        when(request.getParameter(EMAIL)).thenReturn("admw@gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("ads");
        when(request.getParameter(CAPTCHA)).thenReturn("123456");
        when(request.getParameter(CAPTCHA_ID)).thenReturn("0");
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(CAPTCHA_STORE_TYPE)).thenReturn("field");
        when(servletContext.getInitParameter(DB_TYPE)).thenReturn("map");
        when(session.getAttribute(CAPTCHA)).thenReturn(captchaStore);

        Registration registration = new Registration();
        registration.doPost(request, response);

        verify(response, atLeastOnce()).sendRedirect("/shop.html");
    }
}
