package com.epam.verizhenko_andrii.electronicsStore.reflectionInputer;

import java.util.Scanner;

/**
 *basic interface for creating a product with filling in its fields
 * @param <T> - type of created product
 */
public interface ProductService<T> {
    T inputProduct(String productType, Scanner scanner);
}
