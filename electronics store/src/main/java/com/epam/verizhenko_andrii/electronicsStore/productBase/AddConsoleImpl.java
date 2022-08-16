package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.inputer.ConsoleInp;
import com.epam.verizhenko_andrii.electronicsStore.products.Mda;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.util.*;

/**
 * Creating a selected product by entering parameters from the console
 */
public class AddConsoleImpl implements Addable {
    Map<Product,Integer> map = new HashMap<>();
    private String prod;
    private Map<String, Product> productMap = new HashMap<>();
    public AddConsoleImpl() {
        init();
        prod = "prod - products\nmda - mda\nref - refrigeration";
    }
    @Override
    public Map<Product,Integer> add() {
        Scanner sc = new Scanner(System.in);
        System.out.println(prod);
        System.out.println("Enter type products");
        String type = sc.next();
        ConsoleInp consoleInp = new ConsoleInp<>(productMap.get(type));
        int nProducts;
        map.put(consoleInp.inpProd(type,sc), sc.nextInt());
        System.out.println("add more 0/1");
        nProducts = sc.nextInt();
       if (nProducts > 0){
            add();
        }
        return map;
    }

    /**
     *Initialization of products available for creation
     */
    public void init() {
        productMap.put("prod", new Product());
        productMap.put("ref", new Refregerators());
        productMap.put("mda", new Mda());
    }

}
