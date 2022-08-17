package com.epam.verizhenko_andrii.electronicsStore.reflectionInputer;

import com.epam.verizhenko_andrii.electronicsStore.productBase.Addable;
import com.epam.verizhenko_andrii.electronicsStore.products.Mda;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.lang.reflect.Field;
import java.util.*;

public class AddAutogenerateRef implements Addable {
    private final Map<String, Product> productMap = new HashMap<>();

    /**
     * Allowed to add products
     *
     * @return - map of products
     */
    @Override
    public Map<Product, Integer> add() {
        Map<Product, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("prod - products\nmda - mda\nref - refrigeration");
        String pName = sc.next();
        int nProducts = 1;
        while (nProducts > 0) {
            map.put(inpProd(pName), new Random().nextInt(Integer.MAX_VALUE));
            System.out.println("Add more products 0/1");
            nProducts = sc.nextInt();
        }
        return map;
    }

    /**
     * Creates new products with required fields
     *
     * @param pName - type product
     * @return - new Product
     */
    public Product inpProd(String pName) {
        init();
        Product prod = productMap.get(pName);
        Class<Product> clazz = (Class<Product>) prod.getClass();
        while (!clazz.equals(Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            setParam(prod, fields);
            clazz = (Class<Product>) clazz.getSuperclass();
        }
        System.out.println("Enter quantity");
        return prod;

    }

    private void setParam(Product prod, Field[] fields) {

        for (Field field : fields) {
            if (field.getAnnotation(Product.Reflectable.class) != null) {
                field.setAccessible(true);
                try {
                    field.set(prod, typeField(field.getType().getName()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    private Object typeField(String st) {
        if (st.equals("double")) {
            return new Random().nextInt(Integer.MAX_VALUE);
        }
        if (st.equals("int")) {
            return new Random(Integer.MAX_VALUE);
        } else {
            return "Фирма " + new Random().nextInt(Integer.MAX_VALUE);
        }
    }

    public void init() {
        productMap.put("prod", new Product());
        productMap.put("ref", new Refregerators());
        productMap.put("mda", new Mda());
    }

}
