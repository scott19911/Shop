package com.example.electronicshop.utils;

import com.example.electronicshop.dao.ConverterResultSet;
import com.example.electronicshop.dao.MySqlUserDao;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.service.ImageService;
import com.example.electronicshop.service.ImageServiceImpl;
import com.example.electronicshop.service.LoginService;
import com.example.electronicshop.service.LoginUserService;
import com.example.electronicshop.service.RegistrationService;
import com.example.electronicshop.service.RegistrationServiceImpl;
import com.example.electronicshop.service.UploadAvatar;
import com.example.electronicshop.service.UploadService;
import com.example.electronicshop.validate.ValidateLoginForm;
import com.example.electronicshop.validate.ValidateLoginFormImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import static com.example.electronicshop.servlets.DrawImageServlets.IMAGE_SERVICE;
import static com.example.electronicshop.servlets.RegistrationServlets.REGISTRATION_SERVICE;
import static com.example.electronicshop.servlets.UploadFileServlet.UPLOAD_SERVICE;
import static com.example.electronicshop.servlets.UserAuthorizationServlets.LOGIN_SERVICE;

public class ContextListener implements ServletContextListener {
    private ConnectionPool connectionPool;
    private ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
        initPool();
        initService();
    }

    void initPool(){
        connectionPool = new ConnectionPool();
    }
    void initService(){
        ConverterResultSet converterResultSet = new ConverterResultSet();
        UserDao userDao = new MySqlUserDao(converterResultSet, connectionPool);
        ValidateLoginForm validateLoginForm = new ValidateLoginFormImpl();
        LoginService loginService = new LoginUserService(userDao,new TransactionManager(connectionPool),validateLoginForm);
        context.setAttribute(LOGIN_SERVICE,loginService);
        UploadService uploadService = new UploadAvatar();
        context.setAttribute(UPLOAD_SERVICE,uploadService);
        RegistrationService registrationService = new RegistrationServiceImpl();
        context.setAttribute(REGISTRATION_SERVICE,registrationService);
        ImageService imageService = new ImageServiceImpl();
        context.setAttribute(IMAGE_SERVICE,imageService);
    }
}
