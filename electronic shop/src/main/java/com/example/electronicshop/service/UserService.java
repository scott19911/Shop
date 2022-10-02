package com.example.electronicshop.service;

import com.example.electronicshop.users.User;

import java.util.List;

public interface UserService {
    /**
     * Creates a new user, provided that there is no user with this mail yet
     *
     * @param user - new user
     */
    int createUser(User user);

    /**
     * Deletes a user
     *
     * @param userId - deleted user
     */
    boolean deleteUser(int userId);

    /**
     * Updates user information
     *
     * @param user - new user information
     */
    boolean updateUser(User user);

    /**
     * Setting a user avatar link
     *
     * @param userId - user identifier
     * @param path   - link to a picture
     */
    boolean setAvatar(int userId, String path);

    /**
     * Allows you to get a list of all users
     *
     * @return - list of all users
     */
    List<User> getAllUser();

    /**
     * Allows you to get information about the user by his id
     *
     * @param userId - user identifier
     * @return - information about the user
     */
    User getUser(int userId);
}
