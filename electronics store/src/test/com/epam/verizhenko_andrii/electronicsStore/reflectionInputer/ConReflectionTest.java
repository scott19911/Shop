package com.epam.verizhenko_andrii.electronicsStore.reflectionInputer;

import com.epam.verizhenko_andrii.electronicsStore.productBase.AddConsoleImpl;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.After;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConReflectionTest {
    private static final InputStream STD_IN = System.in;
    private static final PrintStream STD_OUT = System.out;

    @After
    public void cleanUp() {
        System.setIn(STD_IN);
        System.setOut(STD_OUT);
    }


    @Test
    void add() {
        Product product = new Product("samsung", 200, 125);
        String data = "prod\nr\nen\nsamsung\n200\n125\n2\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        AddConsoleImpl addConsole = new AddConsoleImpl();
        Map<Product, Integer> act = addConsole.add();
        Integer expQuantity = 2;
        System.setIn(STD_IN);
        for (Map.Entry<Product, Integer> entry : act.entrySet()) {
            assertEquals(product, entry.getKey());
            assertEquals(expQuantity, entry.getValue());
        }
        assertEquals(1, act.size());

    }
}