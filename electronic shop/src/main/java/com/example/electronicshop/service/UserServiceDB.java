package com.example.electronicshop.service;

import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.dao.UserDao;
import com.example.electronicshop.users.User;
import com.example.electronicshop.utils.SecurityPassword;

import java.sql.Connection;
import java.util.List;
import java.util.NoSuchElementException;

public class UserServiceDB implements UserService {
    private UserDao userDao;
    private  TransactionManager transactionManager;

    public UserServiceDB(UserDao userDao,TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public int createUser(User user) {
        SecurityPassword securityPassword = new SecurityPassword();
        String salt = securityPassword.getSalt();
        String cryptoPassword = securityPassword.getHashPassword(user.getPassword() + salt);
        return  transactionManager.doInTransaction( () -> {
            if (userDao.loginUser(user.getEmail()) != null) {
                return 0;
            }
            user.setSalt(salt);
            user.setPassword(cryptoPassword);
            return userDao.addUser(user);
        }, Connection.TRANSACTION_READ_COMMITTED);
    }

    @Override
    public boolean deleteUser(int userId) {

        return transactionManager.doWithoutTransaction(() ->
                userDao.deleteUser(userId)
        );
    }

    @Override
    public boolean updateUser(User user) {
        return transactionManager.doInTransaction(() -> {
            User currentUserData = userDao.selectUserById(user.getId());
            User newUserData = checkingChangedUser(currentUserData, user);
            return userDao.updateUser(newUserData);

        }, Connection.TRANSACTION_READ_COMMITTED);

    }

    @Override
    public boolean setAvatar(int userId, String path) {
        return transactionManager.doWithoutTransaction(
                () -> userDao.addAvatar(userId, path));

    }

    @Override
    public List<User> getAllUser() {
        return transactionManager.doWithoutTransaction(() -> userDao.getAllUser());
    }

    @Override
    public User getUser(int userId) {
        return transactionManager.doWithoutTransaction(() -> {
            if (userDao.selectUserById(userId) == null) {
                throw new NoSuchElementException("No such user");
            }
            return userDao.selectUserById(userId);
        });

    }

    public User checkingChangedUser(User old, User newUser) {
        User resultUser = new User();
        resultUser.setFirstName(checkForNull(newUser.getFirstName(), old.getFirstName()));
        resultUser.setLastName(checkForNull(newUser.getLastName(), old.getLastName()));
        resultUser.setEmail(checkForNull(newUser.getEmail(), old.getEmail()));

        if (newUser.getPassword() == null) {
            resultUser.setPassword(old.getPassword());
            resultUser.setSalt(old.getSalt());
        } else {
            SecurityPassword securityPassword = new SecurityPassword();
            String salt = securityPassword.getSalt();
            resultUser.setSalt(salt);
            resultUser.setPassword(securityPassword.getHashPassword(newUser.getPassword() + salt));
        }
        if (newUser.isMailing() != old.isMailing()) {
            resultUser.setMailing(newUser.isMailing());
        } else {
            resultUser.setMailing(old.isMailing());
        }
        resultUser.setId(old.getId());
        return resultUser;
    }

    private String checkForNull(String newData, String old) {
        if (newData == null) {
            return old;
        }
        return newData;
    }
}
