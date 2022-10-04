package com.example.electronicshop.service.impl;

import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.service.UserService;
import com.example.electronicshop.users.User;

import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceMapStorage implements UserService {
    private final UserDao userDao;

    public UserServiceMapStorage(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int createUser(User user) {
        List<User> allUsers = userDao.getAllUser();
        for (User registeredUser : allUsers) {
            if (user.getEmail().equals(registeredUser.getEmail())) {
                throw new IllegalArgumentException("Already register");
            }
        }
        return userDao.addUser(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean setAvatar(int userId, String path) {
        User user = getUser(userId);
        user.setAvatarUdl(path);
       return updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUser(int userId) {
        if (userDao.selectUserById(userId) == null) {
            throw new NoSuchElementException("No such user");
        }
        return userDao.selectUserById(userId);
    }
}
