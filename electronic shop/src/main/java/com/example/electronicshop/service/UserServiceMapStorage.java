package com.example.electronicshop.service;

import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.users.User;

import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceMapStorage implements UserService {
    private UserDao userDao;

    public UserServiceMapStorage(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int createUser(User user) {
        List<User> allUsers = userDao.getAllUser(null);
        for (User registeredUser : allUsers) {
            if (user.getEmail().equals(registeredUser.getEmail())) {
                throw new IllegalArgumentException("Already register");
            }
        }
       return userDao.addUser(null,user);
    }

    @Override
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(null,userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(null,user);
    }

    @Override
    public boolean setAvatar(int userId, String path) {
        User user = getUser(userId);
        user.setAvatarUdl(path);
       return updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser(null);
    }

    @Override
    public User getUser(int userId) {
        if (userDao.selectUserById(null,userId) == null){
            throw new NoSuchElementException("No such user");
        }
        return userDao.selectUserById(null,userId);
    }
}
