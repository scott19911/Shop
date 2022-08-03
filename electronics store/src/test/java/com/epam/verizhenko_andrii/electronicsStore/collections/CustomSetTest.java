package com.epam.verizhenko_andrii.electronicsStore.collections;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class CustomSetTest {

    @Test
    void set() {
        Product product = new Product("samsung", 0, 0);
        Product product1 = new Product("xiaomi", 0, 0);
        Product product2 = new Product("lg", 0, 0);
        int expectedSize = 2;
        CustomSet<Product> customSet = new CustomSet<>();
        customSet.add(product);
        customSet.add(product1);
        assertThrows(IllegalArgumentException.class,()-> customSet.set(0, product1));
        assertEquals(product, customSet.set(0,product2));
        assertEquals(product2, customSet.get(0));
        assertEquals(expectedSize, customSet.size());
    }

    @Test
    void add() {
        Product product = new Product("samsung",0,0);
        Product product1 = new Product("xiaomi",0,0);
        int expectedSize = 2;
        CustomSet<Product> customSet = new CustomSet<>();
        customSet.add(product);
        customSet.add(product);
        customSet.add(product1);
        assertEquals(expectedSize,customSet.size());
        assertEquals(product,customSet.get(0));
    }

    @Test
    void testAddByIndex() {
        Product product = new Product("samsung", 0, 0);
        Product product1 = new Product("xiaomi", 0, 0);
        int expectedSize = 2;
        CustomSet<Product> customSet = new CustomSet<>();
        customSet.add(product);
        customSet.add(0,product1);
        assertThrows(IllegalArgumentException.class,()-> customSet.add(0, product));
        assertEquals(product1, customSet.get(0));
        assertEquals(product, customSet.get(1));
        assertEquals(expectedSize, customSet.size());
    }

    @Test
    void addAll() {
        Product product = new Product("samsung", 0, 0);
        Product product1 = new Product("xiaomi", 0, 0);
        Product product2 = new Product("lg", 0, 0);
        int expectedSize = 3;
        List<Product> list = Arrays.asList(product,product2, product1);
        CustomSet<Product> customSet = new CustomSet<>();
        customSet.add(product);
        assertTrue(customSet.addAll(list));
        assertEquals(expectedSize,customSet.size());
        assertTrue(customSet.containsAll(list));
    }

    @Test
    void testAddAll() {
        Product product = new Product("samsung", 0, 0);
        Product product1 = new Product("xiaomi", 0, 0);
        Product product2 = new Product("lg", 0, 0);
        int expectedSize = 3;
        List<Product> list = Arrays.asList(product,product2, product1);
        CustomSet<Product> customSet = new CustomSet<>();
        customSet.add(product);
        assertTrue(customSet.addAll(0,list));
        assertFalse(customSet.addAll(1,list));
        assertFalse(customSet.add(product1));
        assertEquals(expectedSize,customSet.size());
        assertTrue(customSet.containsAll(list));
    }

    @Test
    void dublicateElements() {
        Product product = new Product("samsung", 10, 0);
        Product product1 = new Product("xiaomi", 20, 30);
        Product product2 = new Product("lg", 10, 20);
        CustomSet<Product> customSet = new CustomSet<>();
        List<Product> list = Arrays.asList(product,product2, product1);
        int expectedSize = 3;
        UnaryOperator<Product> operator = x-> product;

        customSet.addAll(list);
        assertTrue(customSet.containsAll(list));
        customSet.replaceAll(operator);
        assertEquals(expectedSize, customSet.size());
        assertFalse(customSet.containsAll(list));
        assertEquals(customSet.get(0),customSet.get(1));
        customSet.clear();
        customSet.addAll(list);
        assertNotEquals(customSet.get(0),customSet.get(1));
        product1.setBrand("samsung");
        product1.setPower(10);
        product1.setPrice(0);
        assertEquals(customSet.get(0),customSet.get(2));
    }
}