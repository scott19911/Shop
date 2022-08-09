package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderHistoryDaoImplTest {


    @Test
    void testHistory() {
        OrderHistoryDaoImpl history = new OrderHistoryDaoImpl();
        int expSize = 5;
        Product product = new Product("a",0,0);
        Product product1 = new Product("b",0,0);
        Product product2 = new Product("c",0,0);
        Product product3 = new Product("d",0,0);
        Product product4 = new Product("e",0,0);
        Product product5 = new Product("f",0,0);
        history.addToHistory(0,product);
        history.addToHistory(1,product1);
        history.addToHistory(2,product2);
        history.addToHistory(3,product3);
        history.addToHistory(4,product4);
        history.addToHistory(5,product5);
        assertEquals(expSize, history.getHistory().size());
        assertNull(history.getHistory().get(0));
    }
}