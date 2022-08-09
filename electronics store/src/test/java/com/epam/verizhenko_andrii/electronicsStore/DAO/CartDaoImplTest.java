package com.epam.verizhenko_andrii.electronicsStore.DAO;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class CartDaoImplTest {

    @Test
    void testSetCart() {
        CartDaoImpl cartDao = new CartDaoImpl();
        Hashtable<String, Integer> exp = new Hashtable<>();
        exp.put("lg",4);
        exp.put("samsung",1);
        cartDao.setCart(exp);
        assertEquals(exp,cartDao.getCart());
        assertEquals(exp.get("lg"),cartDao.getCart().get("lg"));
    }



    @Test
    void alreadyAddBrand() {
        CartDaoImpl cartDao = new CartDaoImpl();
        Hashtable<String, Integer> map = new Hashtable<>();
        int expContain = 4;
        int expMissing = -1;
        map.put("lg",4);
        cartDao.setCart(map);
        assertEquals(expContain,cartDao.alreadyAddBrand("lg"));
        assertEquals(expMissing,cartDao.alreadyAddBrand("samsung"));

    }

    @Test
    void addToCart() {
        CartDaoImpl cartDao = new CartDaoImpl();
        Hashtable<String, Integer> map = new Hashtable<>();
        int expContain = 4;
        map.put("lg",4);
        cartDao.addToCart("lg", 4);
        assertEquals(expContain,cartDao.alreadyAddBrand("lg"));
        assertEquals(map, cartDao.getCart());

    }
}