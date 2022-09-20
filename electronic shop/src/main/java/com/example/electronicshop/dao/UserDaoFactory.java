package com.example.electronicshop.dao;

public class UserDaoFactory {
    public static final String MY_SQL = "MY_SQL";
    public UserDao getUserDao(String type){
        if (MY_SQL.equals(type)) {
            return new MySqlUserDao();
        }
        return new UserMapDao();
    }
}
