package com.example.electronicshop.service.impl;

import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.service.LoginService;
import com.example.electronicshop.users.LoginUser;
import com.example.electronicshop.users.SpecificUser;
import com.example.electronicshop.utils.SecurityPassword;
import com.example.electronicshop.validate.ValidateLoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static com.example.electronicshop.constants.ServletsName.LOGIN_SERVLET;
import static com.example.electronicshop.dao.MySqlUserDao.EMAIL;
import static com.example.electronicshop.dao.MySqlUserDao.PASSWORD;
import static com.example.electronicshop.security.SecurityFilter.ERROR_MESSAGE;
import static com.example.electronicshop.security.SecurityFilter.REQUEST_GO_TO;
import static com.example.electronicshop.service.impl.UploadAvatar.SPECIFIC_USER;
import static com.example.electronicshop.servlets.CartServlets.REQUEST_CAME_FROM;

public class LoginUserService implements LoginService {
    public static final String LOGIN_ERROR = "com.example.electronicshop.login.error";
    public static final String PASSWORD_ERROR = "passwordError";
    private final UserDao userDao;
    private final TransactionManager transactionManager;
    private final ValidateLoginForm validateLoginForm;
    public LoginUserService(UserDao userDao, TransactionManager transactionManager,ValidateLoginForm validateLoginForm){
     this.userDao = userDao;
     this.transactionManager = transactionManager;
     this.validateLoginForm = validateLoginForm;
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        LoginUser loginUser = validateLoginForm.readRequest(request);
        Map<String, String> error = validateLoginForm.validate(loginUser);
        String goTo = (String) session.getAttribute(REQUEST_GO_TO);
        String cameFrom;
        if (goTo != null && !goTo.equals(LOGIN_SERVLET)) {
            cameFrom = goTo;
        } else {
            cameFrom = (String) session.getAttribute(REQUEST_CAME_FROM);
        }
        if (error.isEmpty()) {
            error = checkData(session, loginUser);
        }
        if (error.isEmpty()) {
            session.removeAttribute(REQUEST_CAME_FROM);
            session.removeAttribute(REQUEST_GO_TO);
            session.removeAttribute(ERROR_MESSAGE);
            response.sendRedirect(cameFrom);
        } else {
            session.setAttribute(LOGIN_ERROR, error);
            response.sendRedirect(LOGIN_SERVLET);
        }
    }

    private Map<String, String> checkData(HttpSession session, LoginUser loginUser) {
        return transactionManager.doInTransaction(() -> {
            LoginUser dbUser = userDao.loginUser(loginUser.getEmail());
            Map<String, String> error = new HashMap<>();
            if (dbUser != null && !dbUser.isBlocked()) {
                SecurityPassword securityPassword = new SecurityPassword();
                String password = securityPassword.getHashPassword(loginUser.getPassword() + dbUser.getSalt());
                loginAttempt(password,dbUser,session,error);
            } else if (dbUser == null) {
                error.put(EMAIL, "Incorrect email");
            } else {
                error.put(EMAIL, "Account blocked");
            }
            return error;
        }, Connection.TRANSACTION_READ_COMMITTED);
    }
    private void loginAttempt(String password, LoginUser dbUser,HttpSession session, Map<String, String> error){
        if (password.equals(dbUser.getPassword())) {
            SpecificUser specificUser = dbUser.getSpecificUser();
            session.removeAttribute(PASSWORD_ERROR);
            session.setAttribute(SPECIFIC_USER, specificUser);
        } else {
            int tryEnter = 1;
            if (session.getAttribute(PASSWORD_ERROR) != null) {
                tryEnter = (int) session.getAttribute(PASSWORD_ERROR) + 1;
                if (tryEnter % 5 == 0) {
                    userDao.blockUser(dbUser.getUserId());
                }
            }
            session.setAttribute(PASSWORD_ERROR, tryEnter);
            error.put(PASSWORD, "Password doesn't match");
        }
    }
}
