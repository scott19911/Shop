package com.epam.verizhenko_andrii.electronicsStore.server.http;

import com.epam.verizhenko_andrii.electronicsStore.controller.Demo;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class GetItemCommandTest {
    static MockedStatic<Demo> mocked;
    @Test
    public void shouldReturnCount5_WhenProductSize5(){
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
        GetItemCommand getCommand = new GetItemCommand("lg");
        assertEquals("{name: lg, price: 45,00}", getCommand.execute());
        mocked.close();
    }
}
