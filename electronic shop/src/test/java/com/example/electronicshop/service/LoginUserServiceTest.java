package com.example.electronicshop.service;

import com.example.electronicshop.dao.ConverterResultSet;
import com.example.electronicshop.dao.MySqlUserDao;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.service.impl.LoginUserService;
import com.example.electronicshop.users.SpecificUser;
import com.example.electronicshop.utils.ConnectionPool;
import com.example.electronicshop.validate.ValidateLoginFormImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import static com.example.electronicshop.constants.Pages.SHOP_PAGE;
import static com.example.electronicshop.dao.MySqlUserDao.EMAIL;
import static com.example.electronicshop.dao.MySqlUserDao.PASSWORD;
import static com.example.electronicshop.service.impl.UploadAvatar.SPECIFIC_USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginUserServiceTest {
    private static ConnectionPool connectionPool;
    @BeforeEach
    void init() throws SQLException {
        connectionPool = mock(ConnectionPool.class);
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test","root","1991");

        when(connectionPool.getConnection())
                .thenReturn(connection);

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE USERS");
        stmt.executeUpdate("INSERT INTO users (first_name, last_name, email, avatar, mailings, password, salt) VALUES " +
                "('Andrii','Verizhenko','admin@gmail.com',null,true,'28987dc197ac501bf8821b9fa05bf3fb5b3a2a3feb69bd829163aa52023c28f9'," +
                "'27624824')," + "('Andrii','Verizhenko','admin1@gmail.com'," +
                "null,true,'c715dad24defe4b7e53536cb6240d974645d0107956fca65761ddeb7b0dc3d4a'," +
                "'122199100')");

    }

    @Test
    void login() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(EMAIL)).thenReturn("admin@gmail.com");
        when(request.getParameter(PASSWORD)).thenReturn("123");
        LoginUserService loginUserService = new LoginUserService(
                new MySqlUserDao(new ConverterResultSet(),connectionPool),
                new TransactionManager(connectionPool),
                new ValidateLoginFormImpl());
        loginUserService.login(request,response);
        SpecificUser specificUser = mock(SpecificUser.class);
        specificUser.setUserId(1);
        specificUser.setFirstName("Andrii");
        specificUser.setAvatarUrl(null);
        verify(session,atLeastOnce()).setAttribute(eq(SPECIFIC_USER),any());
        verify(response,atLeastOnce()).sendRedirect(SHOP_PAGE);

    }
}