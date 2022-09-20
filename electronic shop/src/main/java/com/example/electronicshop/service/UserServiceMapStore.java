package com.example.electronicshop.service;

import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.users.User;

import java.util.List;

public class UserServiceMapStore implements UserService {
    private UserDao userDao;

    public UserServiceMapStore(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        List<User> allUsers = userDao.getAllUser();
        for (User registeredUser : allUsers) {
            if (user.getEmail().equals(registeredUser.getEmail())) {
                throw new IllegalArgumentException("Already register");
            }
        }
        userDao.addUser(user, user.getPassword());
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user, user.getFirstName());
    }

    @Override
    public void setAvatar(int userId, String path) {
        User user = getUser(userId);
        user.setAvatarUdl(path);
        updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUser(int userId) {
        List<User> userList = userDao.getAllUser();
        return userList.get(userId);
    }
}
