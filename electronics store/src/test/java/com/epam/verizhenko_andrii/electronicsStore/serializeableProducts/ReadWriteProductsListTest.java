package com.epam.verizhenko_andrii.electronicsStore.serializeableProducts;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReadWriteProductsListTest {
    Map<Product,Integer> list;
    Product p1;
    Product p2;
    String fileName;
    String fileName2;

    @BeforeEach
    void init() {
        list = new HashMap<>();
        p1 = new Product("Samsung", 230, 10000);
        p2 = new Product("Lg", 230, 15000);
        list.put(p1,2);
        list.put(p2,3);
        fileName = "test.txt";
        fileName2 = "test2.txt";
    }

    @Test
    void writeToFile() {
        ReadWriteProductsList read = new ReadWriteProductsList();
        read.writeToFile(list, fileName);
        File wr = new File(fileName);
        assertTrue(wr.exists());
        assertTrue(wr.length() > 0);
    }

    @Test
    void writeNTimeToFile() {
        ReadWriteProductsList read = new ReadWriteProductsList();
        read.writeToFile(list, fileName);
        read.writeNTimeToFile(list, fileName2,20);
        read.compressGzipFile(fileName2,fileName2 + ".gz");
        File wr = new File(fileName);
        File wr2 = new File(fileName2);
        File wrGz = new File(fileName2 + ".gz");
        assertTrue(wr2.exists());
        assertTrue(wrGz.exists());
        assertTrue(wr2.length() > wr.length());
        assertTrue(wr2.length() > wrGz.length());
        assertTrue(wr.length() > wrGz.length());
    }


    @Test
    void readProducts() {
        ReadWriteProductsList read = new ReadWriteProductsList();
        Map<Product,Integer> act = read.readProducts(fileName);
        assertEquals(list.get(p1), act.get(p1));
        assertEquals(list.get(p2), act.get(p2));
        assertEquals(list.size(), act.size());
    }
}