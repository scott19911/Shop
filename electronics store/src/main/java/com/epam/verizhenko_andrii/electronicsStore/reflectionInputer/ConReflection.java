package com.epam.verizhenko_andrii.electronicsStore.reflectionInputer;
import com.epam.verizhenko_andrii.electronicsStore.products.Mda;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.lang.reflect.Field;
import java.util.*;

public class ConReflection<T extends Product> implements Add<T> {
    private final Map<String, Product> productMap = new HashMap<>();
    T obj;
    public ConReflection(T obj) {
        this.obj = obj;
        init();
    }
    @Override
    public T inpProd(String pName, Scanner sc) {

        T prod = (T) productMap.get(pName);

        Class<T> clazz = (Class<T>) prod.getClass();

        try {
            while (!clazz.equals(Object.class)){
                Field[] fields =  clazz.getDeclaredFields();
                setParam(sc, prod, fields);
                clazz = (Class<T>) clazz.getSuperclass();
            }
            System.out.println("Enter quantity");
            return prod;

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private void setParam(Scanner sc, T prod, Field[] fields) throws IllegalAccessException {
        System.out.println("Chose language en/ru");
        Locale locale;
        String lan = sc.next();
        if (lan.equalsIgnoreCase("ru")){
            locale =new Locale(lan);
        } else {
            locale =new Locale("");
        }
        ResourceBundle rb = ResourceBundle.getBundle("res", locale);

        for (int i = 0; i < fields.length; i++){
            if(fields[i].getAnnotation(Product.Reflectable.class) != null) {
                String key = fields[i].getAnnotation(Product.Reflectable.class).value();
                System.out.println(rb.getString(key));
                fields[i].setAccessible(true);
                fields[i].set(prod, scan(fields[i].getType().getName(), sc));
            }
        }
    }

    Object scan(String st, Scanner sc){
        if(st.equals("double")){
            return sc.nextDouble();
        } if(st.equals("int")){
            return sc.nextInt();
        }else {
            sc.next();
            return  sc.nextLine();
        }
    }

    public void init() {
        productMap.put("prod",  new Product());
        productMap.put("ref",  new Refregerators());
        productMap.put("mda",  new Mda());
    }

}
