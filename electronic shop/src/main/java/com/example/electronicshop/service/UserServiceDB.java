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
    private TransactionManager transactionManager;

    public UserServiceDB(UserDao userDao) {
        this.userDao = userDao;
        transactionManager = new TransactionManager();
    }

    @Override
    public int createUser(User user) {
        SecurityPassword securityPassword = new SecurityPassword();
        String salt = securityPassword.getSalt();
        String cryptoPassword = securityPassword.getHashPassword(user.getPassword() + salt);
        return (int) (Integer) transactionManager.doInTransaction(connection -> {
            if (userDao.loginUser(connection, user.getEmail()) != null) {
                return 0;
            }
            user.setSalt(salt);
            user.setPassword(cryptoPassword);
            return userDao.addUser(connection, user);
        }, Connection.TRANSACTION_READ_COMMITTED);
    }

    @Override
    public boolean deleteUser(int userId) {
        return (boolean) transactionManager.doWithoutTransaction(connection ->
            userDao.deleteUser(connection, userId)
        );
    }

    @Override
    public boolean updateUser(User user) {
        return (boolean) transactionManager.doInTransaction(connection -> {
            User currentUserData = userDao.selectUserById(connection, user.getId());
            User newUserData = checkingChangedUser(currentUserData, user);
            return userDao.updateUser(connection, newUserData);

        }, Connection.TRANSACTION_READ_COMMITTED);

    }

    @Override
    public boolean setAvatar(int userId, String path) {
        return (boolean) transactionManager.doWithoutTransaction(
                connection -> userDao.addAvatar(connection,userId, path));

    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) transactionManager.doWithoutTransaction(connection->userDao.getAllUser(connection));
    }

    @Override
    public User getUser(int userId) {
        return (User) transactionManager.doWithoutTransaction(connection -> {
            if (userDao.selectUserById(connection,userId) == null) {
                throw new NoSuchElementException("No such user");
            }
            return userDao.selectUserById(connection,userId);
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
