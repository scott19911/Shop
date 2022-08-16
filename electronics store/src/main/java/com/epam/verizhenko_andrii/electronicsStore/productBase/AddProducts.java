package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.AddAutogenerateRef;

import java.util.Map;
import java.util.Scanner;

public class AddProducts {
    public Map<Product,Integer> addProducts(){
        final Addable addProd;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter type of add: c,a,ar");
        String type = sc.next();
        switch (type){
            case "C":
            case "c": addProd = new AddConsoleImpl();
            break;
            case "A":
            case "a": addProd = new AddAutogenerateImpl();
            break;
            case "Ar":
            case "aR":
            case "AR":
            case "ar": addProd = new AddAutogenerateRef();
            break;
            default: addProd = null;
        }

        if(addProd == null){
            System.out.println("unsupported command");
            return null;
        }
        return addProd.add();
    }
}
