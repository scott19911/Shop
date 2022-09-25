package com.example.electronicshop.service;

import com.example.electronicshop.dao.UserDaoFactory;

import static com.example.electronicshop.dao.UserDaoFactory.MY_SQL;

public class UserServiceFactory {
    public UserService getService(String type){
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        if (MY_SQL.equals(type)) {
            return new UserServiceDB(userDaoFactory.getUserDao(type));
        }
        return new UserServiceMapStorage(userDaoFactory.getUserDao(type));
    }
}
