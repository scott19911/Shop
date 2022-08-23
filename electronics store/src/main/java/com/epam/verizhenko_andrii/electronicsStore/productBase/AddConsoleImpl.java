package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.inputer.ConsoleInput;
import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductService;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductServiceAutoGenImpl;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductServiceConsoleImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Creating a selected product by entering parameters from the console
 */
public class AddConsoleImpl implements AddProduct {
    private final String productType;
    private static final String CONSOLE_INPUT = "c";
    private static final String REFLECTION_INPUT = "r";
    private final Map<String, Product> productMap = new HashMap<>();
    Map<Product, Integer> productIntegerMap = new HashMap<>();

    public AddConsoleImpl() {
        init();
        productType = "prod - products\nmda - mda\nref - refrigeration";
    }

    @Override
    public Map<Product, Integer> add() {
        ProductService<Product> consoleInp;
        Scanner scanner = new Scanner(System.in);
        System.out.println(productType);
        System.out.println("Enter type products");
        String type = scanner.next();
        System.out.println("Enter type input r/c");
        String inputType = scanner.next();
        if (inputType.equalsIgnoreCase(REFLECTION_INPUT)) {
            consoleInp = new ProductServiceConsoleImpl<>(productMap.get(type));
        } else if (inputType.equalsIgnoreCase(CONSOLE_INPUT)) {
            consoleInp = new ConsoleInput<>(productMap.get(type));
        } else {
            throw new IllegalArgumentException("Incorrect type of input");
        }
        int moreProducts;
        productIntegerMap.put(consoleInp.inputProduct(type, scanner), scanner.nextInt());
        System.out.println("add more 0/1");
        moreProducts = scanner.nextInt();
        if (moreProducts > 0) {
            add();
        }
        return productIntegerMap;
    }

    /**
     * Initialization of products available for creation
     */
    public void init() {
        productMap.put(ProductServiceAutoGenImpl.PRODUCT, new Product());
        productMap.put(ProductServiceAutoGenImpl.REFREGIRATION, new Refregerators());
        productMap.put(ProductServiceAutoGenImpl.MEDIUM_DIGITAL_APPLIANCE, new MediumDigitalAppliances());
    }

}
