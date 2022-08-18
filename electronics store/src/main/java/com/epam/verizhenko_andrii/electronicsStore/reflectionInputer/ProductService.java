package com.epam.verizhenko_andrii.electronicsStore.reflectionInputer;

import java.util.Scanner;

public interface ProductService<T> {
    T inpProd(String productType, Scanner sc);
}
