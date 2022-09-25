package com.example.electronicshop.dao;

import com.example.electronicshop.users.LoginUser;
import com.example.electronicshop.users.User;
import com.example.electronicshop.utils.SecurityPassword;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserMapDao implements UserDao {
    private static final List<User> users = new ArrayList<>();

    public UserMapDao() {
        init();
    }

    /**
     * Creates list of users with unavailable email
     */
    private void init() {
        if(users.size() == 0) {
            User user1 = new User("adm@gmail.com");
            user1.setId(1);
            User user2 = new User("admin@gmail.com");
            user2.setId(2);
            User user3 = new User("user@gmail.com");
            user3.setId(3);
            User user4 = new User("superUser@gmail.com");
            user4.setId(4);
            users.add(user1);
            users.add(user2);
            users.add(user3);
            users.add(user4);
        }
    }

    @Override
    public List<User> getAllUser(Connection connection) {
        return users;
    }

    @Override
    public boolean updateUser(Connection connection, User newUser) {
        users.set(newUser.getId() - 1, newUser);
        return users.get(newUser.getId() - 1).getEmail().equals(newUser.getEmail());
    }

    @Override
    public boolean deleteUser(Connection connection,int id) {
        int userSize = users.size();
        users.remove(id);
        return userSize == users.size() + 1;
    }

    @Override
    public int addUser(Connection connection, User user) {
        user.setId(users.size() + 1);
        SecurityPassword securityPassword = new SecurityPassword();
        String salt = securityPassword.getSalt();
        user.setSalt(salt);
        String newPassword = securityPassword.getHashPassword(user.getPassword() + salt);
        user.setPassword(newPassword);
        users.add(user);
        return users.size();
    }


    @Override
    public boolean addAvatar(Connection connection,int id, String path) {
        User user = selectUserById(connection,id);
        user.setAvatarUdl(path);
        updateUser(connection,user);
        return selectUserById(connection,id).getAvatarUrl().equals(path);
    }

    @Override
    public LoginUser loginUser(Connection connection,String email) {
        List<User> userList = getAllUser(connection);
        LoginUser authorizationUser = new LoginUser();
        for (User user : userList
        ) {
            if (user.getEmail().equals(email)) {
                authorizationUser.setSalt(user.getSalt());
                authorizationUser.setPassword(user.getPassword());
                authorizationUser.setEmail(email);
                authorizationUser.setUserId(user.getId());
                authorizationUser.setAvatarUrl(user.getAvatarUrl());
                authorizationUser.setFirstName(user.getFirstName());
                return authorizationUser;
            }
        }
        return null;
    }

    @Override
    public User selectUserById(Connection connection,int userId) {
        if (userId > users.size()){
            return null;
        }
        return users.get(userId - 1);
    }
}
