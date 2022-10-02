package com.example.electronicshop.service;

import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.users.LoginUser;
import com.example.electronicshop.users.SpecificUser;
import com.example.electronicshop.utils.SecurityPassword;
import com.example.electronicshop.validate.ValidateLoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.electronicshop.constants.ServletsName.LOGIN_SERVLET;
import static com.example.electronicshop.constants.ServletsName.PRODUCT_LIST;
import static com.example.electronicshop.dao.MySqlUserDao.EMAIL;
import static com.example.electronicshop.dao.MySqlUserDao.PASSWORD;
import static com.example.electronicshop.service.UploadAvatar.SPECIFIC_USER;

public class LoginUserService implements LoginService{
    public static final String LOGIN_ERROR = "com.example.electronicshop.login.error";
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
        Map<String,String> error = validateLoginForm.validate(loginUser);
        if (error.isEmpty()){
          LoginUser dbUser = transactionManager.doWithoutTransaction(() -> userDao.loginUser(loginUser.getEmail()));
          if(dbUser != null) {
              SecurityPassword securityPassword = new SecurityPassword();
              String password = securityPassword.getHashPassword(loginUser.getPassword() + dbUser.getSalt());
              if (password.equals(dbUser.getPassword())) {
                  SpecificUser specificUser = dbUser.getSpecificUser();
                  session.setAttribute(SPECIFIC_USER,specificUser);
              }else {
                  error.put(PASSWORD,"Password doesn't match");
              }
          } else {
              error.put(EMAIL,"Incorrect email");
          }
        }
        if (error.isEmpty()) {
            response.sendRedirect(PRODUCT_LIST);
        } else {
            session.setAttribute(LOGIN_ERROR, error);
            response.sendRedirect(LOGIN_SERVLET);
        }
    }

}
