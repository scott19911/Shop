package com.example.electronicshop.dao;

import com.example.electronicshop.utils.ConnectionPool;

public class UserDaoFactory {
    public static final String MY_SQL = "MY_SQL";
    public UserDao getUserDao(String type){
        if (MY_SQL.equals(type)) {
            return new MySqlUserDao(new ConverterResultSet(),new ConnectionPool());
        }
        return new UserMapDao();
    }
}
