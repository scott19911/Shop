package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductService;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductServiceAutoGenImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Input product parameters by console
 * @param <T> - product type
 */
public class ConsoleInput<T extends Product> implements ProductService<T> {
    public final Map<String, Product> productMap = new HashMap<>();
    public final Map<String, Inputer<T>> inputerMap = new HashMap<>();
    public T obj;
    public ConsoleInput(Object obj) {
        init();
        this.obj = (T) obj;
    }

    /**
     * Depending on the passed object type parameter, it calls the necessary inputer
     *
     * @param productType - type product
     * @return - product
     */
    @Override
    public T inputProduct(String productType, Scanner scanner) {
        Inputer<T> inputer = inputerMap.get(productType);
        T product = (T) productMap.get(productType);
        if (!obj.getClass().isAssignableFrom(product.getClass())) {
            throw new IllegalArgumentException("Product type mismatch");
        }
        return inputer.input(product, scanner);
    }

    /**
     * Set available products
     *
     * @param key     - product name for access
     * @param inputer - inputer
     * @param product - product class
     */
    public void mapProducts(String key, Inputer<T> inputer, Product product) {
        inputerMap.put(key, inputer);
        productMap.put(key, product);
    }

    public void init() {
        mapProducts(ProductServiceAutoGenImpl.PRODUCT, new InputerProducts<>(), new Product());
        mapProducts(ProductServiceAutoGenImpl.REFREGIRATION, new InputerRefregerators(), new Refregerators());
        mapProducts(ProductServiceAutoGenImpl.MEDIUM_DIGITAL_APPLIANCE, new InputerMediumDigitalAppliances(), new MediumDigitalAppliances());
    }

}
