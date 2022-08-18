package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;

public interface Addable {
    Map<Product, Integer> add();
}
