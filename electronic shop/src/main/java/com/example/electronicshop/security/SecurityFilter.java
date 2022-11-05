package com.example.electronicshop.security;


import com.example.electronicshop.users.SpecificUser;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.electronicshop.constants.Pages.INDEX_PAGE;
import static com.example.electronicshop.service.impl.UploadAvatar.SPECIFIC_USER;
import static com.example.electronicshop.servlets.CartServlets.REQUEST_CAME_FROM;


@WebFilter("/*")
public class SecurityFilter implements Filter {
    public final static String ERROR_MESSAGE = "errMessage";
    public final static String REQUEST_GO_TO = "goTo";

    public SecurityFilter() {
        //default constructor
    }

    @Override
    public void destroy() {
        //default
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        SpecificUser loginUser = (SpecificUser) session.getAttribute(SPECIFIC_USER);
        HttpServletRequest wrapRequest = request;
        String  referrer = request.getHeader("referer") == null ? INDEX_PAGE : request.getHeader("referer");
        if (loginUser != null) {
            int userId = loginUser.getUserId();
            String roles = loginUser.getUserRole();
            wrapRequest = new UserRoleRequestWrapper(userId, roles, request);
        }
        if (SecurityUtils.isSecurityPage(request)) {
            if (loginUser == null) {
                String goTo = request.getRequestURI();
                session.setAttribute(REQUEST_GO_TO, goTo);
                session.setAttribute(ERROR_MESSAGE,"Sorry you need Sign in");
                response.sendRedirect(referrer);
                return;
            }
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {
                request.setAttribute(ERROR_MESSAGE, "Sorry you can't go there");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
                session.setAttribute(REQUEST_CAME_FROM, referrer);
                dispatcher.forward(request, response);
                return;
            }
        }
        session.setAttribute(REQUEST_CAME_FROM, referrer);
        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        //default
    }


}