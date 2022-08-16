package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Scanner;

public interface Inputer<T extends Product> {
    T inp(T t, Scanner sc);
}

