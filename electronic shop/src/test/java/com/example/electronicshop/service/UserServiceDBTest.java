package com.example.electronicshop.service;

import com.example.electronicshop.dao.ConverterResultSet;
import com.example.electronicshop.dao.MySqlUserDao;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.service.impl.UserServiceDB;
import com.example.electronicshop.users.User;
import com.example.electronicshop.utils.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceDBTest {
    private static ConnectionPool connectionPool;

    @BeforeEach
    void init() throws SQLException {
        connectionPool = mock (ConnectionPool.class);
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
    void shouldReturn0_whenEntered_registerEmail() {
        UserServiceDB userServiceDB = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), connectionPool),
                new TransactionManager(connectionPool));
        int expected = 0;
        User user = new User("admin@gmail.com");
        assertEquals(expected,userServiceDB.createUser(user));

    }
    @Test
    void shouldReturn3_whenEntered_newUser() {
        UserServiceDB userServiceDB = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), connectionPool),
                new TransactionManager(connectionPool));
        int expected = 3;
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("123");
        user.setFirstName("test");
        user.setLastName("ewe");
        user.setMailing(false);
        assertEquals(expected,userServiceDB.createUser(user));

    }

    @Test
    void shouldReturnList_ofUsers_withTwoUsers_whenGetAllUser() {
        UserServiceDB userServiceDB = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), connectionPool),
                new TransactionManager(connectionPool));
        List<User> userList = userServiceDB.getAllUser();
        String[] expectedEmail = {"admin@gmail.com", "admin1@gmail.com"};
        int expectedLength = 2;
        assertEquals(expectedLength,userList.size());
        assertEquals(expectedEmail[0],userList.get(0).getEmail());
        assertEquals(expectedEmail[1],userList.get(1).getEmail());

    }
    @Test
    void shouldDeleteUser_whenUserIdIsExist() throws SQLException {
        UserServiceDB userServiceDB = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), connectionPool),
                new TransactionManager(connectionPool));
        List<User> userList = userServiceDB.getAllUser();
        String[] expectedEmail = {"admin@gmail.com", "admin1@gmail.com"};
        int expectedLength = 2;
        int expectedLengthAfterDelete = 1;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test","root","1991");
        ConnectionPool dbUtils = mock(ConnectionPool.class);
        when(dbUtils.getConnection())
                .thenReturn(connection);
        UserServiceDB userServiceDB0 = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), dbUtils),
                new TransactionManager(dbUtils));
        assertEquals(expectedLength,userList.size());
        userServiceDB0.deleteUser(userList.get(0).getId());

        Connection connection1 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test","root","1991");
        ConnectionPool dbUtils1 = mock(ConnectionPool.class);
        when(dbUtils1.getConnection())
                .thenReturn(connection1);
        UserServiceDB userServiceDB1 = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), dbUtils1),
                new TransactionManager(dbUtils1));

        List<User> userList1 = userServiceDB1.getAllUser();
        assertEquals(expectedLengthAfterDelete,userList1.size());
        assertEquals(expectedEmail[1],userList1.get(0).getEmail());



    }

    @Test
    void shouldUpdateUser_whenEnteredNewUserData() throws SQLException {
        UserServiceDB userServiceDB = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), connectionPool),
                new TransactionManager(connectionPool));
        User newUser = new User();
        newUser.setId(1);
        newUser.setEmail("newEmail@gmaile.com");
        userServiceDB.updateUser(newUser);
        Connection connection1 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test","root","1991");
        ConnectionPool dbUtils1 = mock(ConnectionPool.class);
        when(dbUtils1.getConnection())
                .thenReturn(connection1);

        UserServiceDB userServiceDB1 = new UserServiceDB(new MySqlUserDao(new ConverterResultSet(), dbUtils1),
                new TransactionManager(dbUtils1));
        List<User> userList1 = userServiceDB1.getAllUser();
        assertEquals(newUser.getEmail(),userList1.get(0).getEmail());

    }

}