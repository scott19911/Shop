package com.epam.verizhenko_andrii.electronicsStore.server.custom;

import com.epam.verizhenko_andrii.electronicsStore.controller.Demo;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;


public class CustomServerCommandsTest {
    static MockedStatic<Demo> mocked;
    @Test
    public void getCountTest_shouldReturn5_WhenProductSize5() {
        mocked = mockStatic(Demo.class);
        Map<Product,Integer> expected = new HashMap<>();
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        mocked.when(Demo::getProductsMap).thenReturn(expected);
        CustomServerCommands commands = new CustomServerCommands();
        assertEquals(5, commands.getCount());
        mocked.close();
    }
    @Test
    public void getItemTest_shouldReturn_lg45_WhenItemLg() {
        mocked = mockStatic(Demo.class);
        Map<Product,Integer> expected = new HashMap<>();
        Product product = new Product();
        product.setBrand("lg");
        product.setPrice(45);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        expected.put(product,1);
        expected.put(new Product(),1);
        expected.put(new Product(),1);
        mocked.when(Demo::getProductsMap).thenReturn(expected);
        CustomServerCommands commands = new CustomServerCommands();
        assertEquals("lg|45.0", commands.getItem("lg"));
        mocked.close();
    }
}
