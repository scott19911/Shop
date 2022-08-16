package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.Mda;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.Add;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class ConsoleInp<T extends Product> implements Add<T> {
    private final Map<String, Product> productMap = new HashMap<>();
    private final Map<String, Inputer<T>> inputerMap = new HashMap<>();
    T obj;
    public ConsoleInp(Object obj) {
        init();
        this.obj =(T) obj;
    }

    /**
     *Depending on the passed object type parameter, it calls the necessary inputer
     * @param pName - type product
     * @return - product
     */
    @Override
    public T inpProd(String pName, Scanner sc) {
        Inputer<T> inp = inputerMap.get(pName);
        T prod = (T) productMap.get(pName);

        if (!obj.getClass().isAssignableFrom(prod.getClass())) {
            throw new IllegalArgumentException("Product type mismatch");
        }
        return inp.inp(prod, sc);
    }

    /**
     * Set available products
     * @param key - product name for access
     * @param inp - inputer
     * @param product - product class
     */
    public void mapProducts(String key, Inputer<T> inp, Product product) {
        inputerMap.put(key, inp);
        productMap.put(key, product);
    }

    public void init() {
        mapProducts("prod", new InputerProducts(), new Product());
        mapProducts("ref", new InputerRefregerators(), new Refregerators());
        mapProducts("mda", new InputerMda(), new Mda());
    }

}
