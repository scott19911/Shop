package com.example.electronicshop.dao;

import com.example.electronicshop.users.AuthorizationUser;
import com.example.electronicshop.users.User;

import java.util.List;

public interface UserDao {
    /**
     * Returns a list of all users
     *
     * @return - list of all users
     */
    List<User> getAllUser();

    /**
     * Allows you to update the user profile
     *
     * @param newUser - new user data
     */
    void updateUser(User newUser, String salt);

    /**
     * Removes a user from the list
     *
     * @param id - user ID
     */
    void deleteUser(int id);

    /**
     * add new user
     *
     * @param user - user data
     */
    int addUser(User user, String salt);

    /**
     * * allows set avatar
     *
     * @param id   - user id
     * @param path - to avatar
     */
    void addAvatar(int id, String path);

    /**
     * Returns data for user authorization
     *
     * @param email - user email
     * @return - data for user authorization
     */
    AuthorizationUser loginUser(String email);

}
