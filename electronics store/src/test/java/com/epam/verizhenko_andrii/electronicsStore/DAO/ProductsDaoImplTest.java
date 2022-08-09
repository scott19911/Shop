package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductsDaoImplTest {

    Map<Product, Integer> productsMap;
    Product product;
    Product product1;
    @BeforeEach
    void init(){
        productsMap = new HashMap<>();
        product = new Product("a",0,0);
        product1 = new Product("b",0,0);
        Product product2 = new Product("c",0,0);
        Product product3 = new Product("d",0,0);
        Product product4 = new Product("e",0,0);
        Product product5 = new Product("f",0,0);
        productsMap.put(product,4);
        productsMap.put(product1,4);
        productsMap.put(product2,4);
        productsMap.put(product3,4);
        productsMap.put(product4,4);
        productsMap.put(product5,4);
    }

    @AfterEach
    void clear(){
        productsMap.clear();
    }

    @Test
    void testGetandSetProducts() {
        ProductsDaoImpl productsDao = new ProductsDaoImpl();
        productsDao.setProducts(productsMap);
        assertEquals(productsMap, productsDao.getProducts());

    }

    @Test
    void updateProducts() {
        ProductsDaoImpl productsDao = new ProductsDaoImpl();
        Product product = new Product("a",0,0);
        int exp = 10;
        productsDao.setProducts(productsMap);
        productsDao.updateProducts(product,exp);
        assertEquals(exp, productsDao.getProducts().get(product));
    }

    @Test
    void getProduct() {
        ProductsDaoImpl productsDao = new ProductsDaoImpl();
        Product exp = new Product("a",0,0);
        productsDao.setProducts(productsMap);

        assertEquals(exp, productsDao.getProduct("a"));
        assertNull(productsDao.getProduct("h"));
    }

    @Test
    void getQuantity() {
        ProductsDaoImpl productsDao = new ProductsDaoImpl();
        int exp = 4;
        int exp1 = 12;
        productsDao.setProducts(productsMap);
        productsDao.updateProducts(product1, 12);

        assertEquals(exp, productsDao.getQuantity(product));
        assertEquals(exp1, productsDao.getQuantity(product1));

    }
}