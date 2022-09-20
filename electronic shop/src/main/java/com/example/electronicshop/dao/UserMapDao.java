package com.example.electronicshop.dao;

import com.example.electronicshop.users.AuthorizationUser;
import com.example.electronicshop.users.User;

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
        users.add(new User("adm@gmail.com"));
        users.add(new User("admin@gmail.com"));
        users.add(new User("user@gmail.com"));
        users.add(new User("superUser@gmail.com"));
    }

    @Override
    public List<User> getAllUser() {
        return users;
    }

    @Override
    public void updateUser(User newUser, String salt) {
        users.set(newUser.getId(), newUser);
    }

    @Override
    public void deleteUser(int id) {
        users.remove(id);
    }

    @Override
    public int addUser(User user, String salt) {
        user.setId(users.size());
        users.add(user);
        return users.size() - 1;
    }


    @Override
    public void addAvatar(int id, String path) {
        List<User> userList = getAllUser();
        User user = userList.get(id);
        user.setAvatarUdl(path);
        updateUser(user, user.getLastName());
    }

    @Override
    public AuthorizationUser loginUser(String email) {
        List<User> userList = getAllUser();
        AuthorizationUser authorizationUser = new AuthorizationUser();
        for (User user : userList
        ) {
            if (user.getEmail().equals(email)) {
                authorizationUser.setSalt(user.getLastName());
                authorizationUser.setPassword(user.getPassword());
                authorizationUser.setEmail(email);
                authorizationUser.setUserId(user.getId());
            }
        }
        return null;
    }
}
