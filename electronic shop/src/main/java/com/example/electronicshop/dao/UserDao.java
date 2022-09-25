package com.example.electronicshop.dao;

import com.example.electronicshop.users.LoginUser;
import com.example.electronicshop.users.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    /**
     * Returns a list of all users
     *
     * @return - list of all users
     */
    List<User> getAllUser(Connection connection);

    /**
     * Get user by id
     * @param userId - searching user id
     * @return - user
     */
    User selectUserById(Connection connection, int userId);

    /**
     * Allows you to update the user profile
     *
     * @param newUser - new user data
     */
    boolean updateUser(Connection connection,User newUser);

    /**
     * Removes a user from the list
     *
     * @param id - user ID
     */
    boolean deleteUser(Connection connection,int id);

    /**
     * add new user
     *
     * @param user - user data
     */
    int addUser(Connection connection,User user);

    /**
     * * allows set avatar
     *
     * @param id   - user id
     * @param path - to avatar
     */
    boolean addAvatar(Connection connection,int id, String path);

    /**
     * Returns data for user authorization
     *
     * @param email - user email
     * @return - data for user authorization
     */
    LoginUser loginUser(Connection connection,String email);


}
