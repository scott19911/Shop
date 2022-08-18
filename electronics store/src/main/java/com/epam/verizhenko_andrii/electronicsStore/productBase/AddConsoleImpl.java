package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.inputer.ConsoleInp;
import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductService;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductServiceConsoleImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Creating a selected product by entering parameters from the console
 */
public class AddConsoleImpl implements Addable {
    private final String prod;
    private static final String CONSOLE_INPUT = "c";
    private static final String REFLECTION_INPUT = "r";
    private final Map<String, Product> productMap = new HashMap<>();
    Map<Product, Integer> productIntegerMap = new HashMap<>();

    public AddConsoleImpl() {
        init();
        prod = "prod - products\nmda - mda\nref - refrigeration";
    }

    @Override
    public Map<Product, Integer> add() {
        ProductService<Product> consoleInp;
        Scanner sc = new Scanner(System.in);
        System.out.println(prod);
        System.out.println("Enter type products");
        String type = sc.next();
        System.out.println("Enter type input r/c");
        String cl = sc.next();

        if (cl.equalsIgnoreCase(REFLECTION_INPUT)) {
            consoleInp = new ProductServiceConsoleImpl<>(productMap.get(type));
        } else if (cl.equalsIgnoreCase(CONSOLE_INPUT)) {
            consoleInp = new ConsoleInp<>(productMap.get(type));
        } else {
            throw new IllegalArgumentException("Incorrect type of input");
        }

        int nProducts;
        productIntegerMap.put(consoleInp.inpProd(type, sc), sc.nextInt());
        System.out.println("add more 0/1");
        nProducts = sc.nextInt();
        if (nProducts > 0) {
            add();
        }
        return productIntegerMap;
    }

    /**
     * Initialization of products available for creation
     */
    public void init() {
        productMap.put("prod", new Product());
        productMap.put("ref", new Refregerators());
        productMap.put("mda", new MediumDigitalAppliances());
    }

}
