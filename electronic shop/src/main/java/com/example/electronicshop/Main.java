package com.example.electronicshop;

import com.example.electronicshop.dao.ProductRepositoryImpl.Builder;

public class Main {
    public static void main(String[] args) {
        String mySqlUserDao = new Builder().build();

        System.out.println(mySqlUserDao);
    }
}
