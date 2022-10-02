package com.example.electronicshop.dao;

import com.example.electronicshop.users.LoginUser;
import com.example.electronicshop.users.User;
import com.example.electronicshop.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySqlUserDao implements UserDao {
    public static final String USER_ID = "id";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String AVATAR_URL = "AVATAR";
    public static final String RECEIVE_MAILING = "MAILINGS";
    public static final String PASSWORD = "password";
    public static final String SALT = "salt";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM USERS;";
    private static final String UPDATE_USER = "UPDATE USERS SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, " +
            "MAILINGS=?, PASSWORD=?, SALT=? WHERE ID=?;";
    private static final String DELETE_USER_BY_ID = "DELETE FROM USERS WHERE ID=?;";
    private static final String INSERT_USER = "INSERT INTO USERS (first_name,last_name,email," +
            "password,salt,mailings,avatar) VALUES (?,?,?,?,?,?,?);";
    private static final String UPDATE_USER_AVATAR = "UPDATE USERS SET AVATAR=? WHERE ID=?;";
    private static final String SELECT_LOGIN_INFORMATION_BY_EMAIL = "SELECT id, password, salt, avatar, first_name FROM users WHERE email=?";
    public ConverterResultSet converterResultSet;
    public ConnectionPool pool;

    public MySqlUserDao(ConverterResultSet converterResultSet, ConnectionPool pool) {
        this.converterResultSet = converterResultSet;
        this.pool = pool;
    }

    @Override
    public List<User> getAllUser() {
        Connection connection = pool.getConnection();
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SELECT_ALL_USERS);
            return converterResultSet.getListUser(rs);
        } catch (SQLException ex) {
            throw new RuntimeException("Sorry, cannot load list of users");
        }
    }

    @Override
    public boolean updateUser(User newUser) {
        Connection con = pool.getConnection();
        try (PreparedStatement stm = con.prepareStatement(UPDATE_USER)) {
            stm.setString(1, newUser.getFirstName());
            stm.setString(2, newUser.getLastName());
            stm.setString(3, newUser.getEmail());
            stm.setBoolean(4, newUser.isMailing());
            stm.setString(5, newUser.getPassword());
            stm.setString(6, newUser.getSalt());
            stm.setInt(7, newUser.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        Connection con = pool.getConnection();
        try (PreparedStatement stm = con.prepareStatement(DELETE_USER_BY_ID)) {
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int addUser( User user) {
        Connection con = pool.getConnection();
        try (PreparedStatement stm = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getLastName());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getPassword());
            stm.setString(5, user.getSalt());
            stm.setString(7, user.getAvatarUrl());
            stm.setBoolean(6, user.isMailing());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

    @Override
    public boolean addAvatar( int id, String path) {
        Connection con = pool.getConnection();
        try (PreparedStatement stm = con.prepareStatement(UPDATE_USER_AVATAR)) {
            stm.setString(1, path);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public LoginUser loginUser(String email) {
        LoginUser authorizationUser = new LoginUser();
        Connection con = pool.getConnection();
        try (PreparedStatement stm = con.prepareStatement(SELECT_LOGIN_INFORMATION_BY_EMAIL)) {
            stm.setString(1, email);
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            if (resultSet.next()) {
                authorizationUser.setPassword(resultSet.getString(PASSWORD));
                authorizationUser.setSalt(resultSet.getString(SALT));
                authorizationUser.setEmail(email);
                authorizationUser.setUserId(resultSet.getInt(USER_ID));
                authorizationUser.setAvatarUrl(resultSet.getString(AVATAR_URL));
                authorizationUser.setFirstName(resultSet.getString(FIRST_NAME));
                return authorizationUser;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public User selectUserById( int userId) {
        Connection con = pool.getConnection();
        try (PreparedStatement stm = con.prepareStatement(SELECT_USER_BY_ID)) {
            stm.setInt(1, userId);
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            return converterResultSet.getUser(resultSet);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
