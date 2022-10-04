package com.example.electronicshop.service.impl;

import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.dao.UserDaoFactory;
import com.example.electronicshop.service.UserService;
import com.example.electronicshop.utils.ConnectionPool;

import static com.example.electronicshop.dao.UserDaoFactory.MY_SQL;

public class UserServiceFactory {
    public UserService getService(String type){
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        if (MY_SQL.equals(type)) {
            return new UserServiceDB(userDaoFactory.getUserDao(type),new TransactionManager(new ConnectionPool()));
        }
        return new UserServiceMapStorage(userDaoFactory.getUserDao(type));
    }
}
