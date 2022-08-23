package com.epam.verizhenko_andrii.electronicsStore.reflectionInputer;

import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
/**
 *  creates a  product with fields filled
 *  based on reflection
 */
public class ProductServiceConsoleImpl<T extends Product> implements ProductService<T> {
    private final Map<String, Product> productMap = new HashMap<>();
    private static final String RESOURCE_BUNDLE = "res";
    private static final String RU_LANGUAGE = "ru";
    T obj;

    public ProductServiceConsoleImpl(T obj) {
        this.obj = obj;
        init();
    }

    @Override
    public T inputProduct(String productType, Scanner scanner) {
        T product = (T) productMap.get(productType);
        Class<T> clazz = (Class<T>) product.getClass();
        try {
            while (!clazz.equals(Object.class)) {
                Field[] fields = clazz.getDeclaredFields();
                setParameter(scanner, product, fields);
                clazz = (Class<T>) clazz.getSuperclass();
            }
            System.out.println("Enter quantity");
            return product;
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setParameter(Scanner scanner, T product, Field[] fields) throws IllegalAccessException {
        System.out.println("Chose language en/ru");
        Locale locale;
        String language = scanner.next();
        if (language.equalsIgnoreCase(RU_LANGUAGE)) {
            locale = new Locale(language);
        } else {
            locale = new Locale("");
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE, locale);
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getAnnotation(Product.Reflectable.class) != null) {
                String key = fields[i].getAnnotation(Product.Reflectable.class).value();
                System.out.println(resourceBundle.getString(key));
                fields[i].setAccessible(true);
                fields[i].set(product, returnScannerType(fields[i].getType().getName(), scanner));
            }
        }
    }

    Object returnScannerType(String typeFields, Scanner scanner) {
        if (typeFields.equals(ProductServiceAutoGenImpl.DOUBLE)) {
            return scanner.nextDouble();
        }
        if (typeFields.equals(ProductServiceAutoGenImpl.INT)) {
            return scanner.nextInt();
        } else {
            scanner.nextLine();
            return scanner.nextLine();
        }
    }

    public void init() {
        productMap.put(ProductServiceAutoGenImpl.PRODUCT, new Product());
        productMap.put(ProductServiceAutoGenImpl.REFREGIRATION, new Refregerators());
        productMap.put(ProductServiceAutoGenImpl.MEDIUM_DIGITAL_APPLIANCE, new MediumDigitalAppliances());
    }

}
