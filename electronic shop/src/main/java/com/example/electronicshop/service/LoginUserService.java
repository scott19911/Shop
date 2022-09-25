package com.example.electronicshop.service;

import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.users.LoginUser;
import com.example.electronicshop.users.SpecificUser;
import com.example.electronicshop.utils.SecurityPassword;
import com.example.electronicshop.validate.ValidateLoginForm;
import com.example.electronicshop.validate.ValidateLoginFormImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.electronicshop.constants.Pages.SHOP_PAGE;
import static com.example.electronicshop.constants.ServletsName.LOGIN_SERVLET;
import static com.example.electronicshop.dao.MySqlUserDao.EMAIL;
import static com.example.electronicshop.dao.MySqlUserDao.PASSWORD;
import static com.example.electronicshop.service.UploadAvatar.SPECIFIC_USER;

public class LoginUserService implements LoginService{
    public static final String LOGIN_ERROR = "com.example.electronicshop.login.error";
    UserDao userDao;
    public LoginUserService(UserDao userDao){
     this.userDao = userDao;
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TransactionManager transactionManager = new TransactionManager();
        ValidateLoginForm validateLoginForm = new ValidateLoginFormImpl();
        HttpSession session = request.getSession();
        LoginUser loginUser = validateLoginForm.readRequest(request);
        Map<String,String> error = validateLoginForm.validate(loginUser);
        if (error.isEmpty()){
          LoginUser dbUser = (LoginUser) transactionManager.doWithoutTransaction(connection -> userDao.loginUser(connection,loginUser.getEmail()));
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
            response.sendRedirect(SHOP_PAGE);
        } else {
            session.setAttribute(LOGIN_ERROR, error);
            response.sendRedirect(LOGIN_SERVLET);
        }
    }

}
