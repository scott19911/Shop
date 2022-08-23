package com.epam.verizhenko_andrii.electronicsStore.reflectionInputer;

import com.epam.verizhenko_andrii.electronicsStore.productBase.AddProduct;
import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * automatically creates a random product with fields filled with randomly generated data
 */
public class ProductServiceAutoGenImpl implements AddProduct {
    private final Map<String, Product> productMap = new HashMap<>();
    public static final String PRODUCT = "prod";
    public static final String MEDIUM_DIGITAL_APPLIANCE = "mda";
    public static final String REFREGIRATION = "ref";
    public static final String DOUBLE = "double";
    public static final String INT = "int";
    private final int NUMBER_OF_PRODUCTS = 3;

    /**
     * Allowed to add products
     *
     * @return - map of products
     */
    @Override
    public Map<Product, Integer> add() {
        Map<Product, Integer> productIntegerHashMap = new HashMap<>();
        int addProducts = 1;
        while (addProducts > 0) {
            String productType = randomProduct(new Random().nextInt(NUMBER_OF_PRODUCTS));
            productIntegerHashMap.put(inputProduct(productType), new Random().nextInt(Integer.MAX_VALUE));
            addProducts = new Random().nextInt(NUMBER_OF_PRODUCTS);
        }
        return productIntegerHashMap;
    }

    private String randomProduct(int number) {
        switch (number) {
            case 0:
                return MEDIUM_DIGITAL_APPLIANCE;
            case 1:
                return REFREGIRATION;
            default:
                return PRODUCT;
        }

    }

    /**
     * Creates new products with required fields
     *
     * @param productType - type product
     * @return - new Product
     */
    public Product inputProduct(String productType) {
        init();
        Product prod = productMap.get(productType);
        Class<Product> clazz = (Class<Product>) prod.getClass();
        while (!clazz.equals(Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            setParameter(prod, fields);
            clazz = (Class<Product>) clazz.getSuperclass();
        }
        return prod;
    }

    private void setParameter(Product prod, Field[] fields) {

        for (Field field : fields) {
            if (field.getAnnotation(Product.Reflectable.class) != null) {
                field.setAccessible(true);
                try {
                    field.set(prod, typeField(field.getType().getName()));
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private Object typeField(String typeField) {
        if (typeField.equals(DOUBLE)) {
            return new Random().nextInt(Integer.MAX_VALUE);
        }
        if (typeField.equals(INT)) {
            return new Random(Integer.MAX_VALUE);
        } else {
            return "Company " + new Random().nextInt(Integer.MAX_VALUE);
        }
    }

    public void init() {
        productMap.put(PRODUCT, new Product());
        productMap.put(REFREGIRATION, new Refregerators());
        productMap.put(MEDIUM_DIGITAL_APPLIANCE, new MediumDigitalAppliances());
    }

}
