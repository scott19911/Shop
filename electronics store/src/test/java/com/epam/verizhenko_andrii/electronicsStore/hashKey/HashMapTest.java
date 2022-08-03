package com.epam.verizhenko_andrii.electronicsStore.hashKey;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class HashMapTest {

    @Test
    void hashLength(){
        Product product = new Product("samsung", 10, 0);
        Product product1 = new Product("xiaomi", 20, 30);
        HashMap<HashLength, Product> hashMap = new HashMap<>();
        hashMap.put(new HashLength("1"),product);
        hashMap.put(new HashLength("32"),product);
        hashMap.put(new HashLength("112"),product1);
        hashMap.put(new HashLength("2"),product1);
        Iterator<Map.Entry<HashLength, Product>> itr = hashMap.entrySet().iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
    @Test
    void hashSumFour(){
        Product product = new Product("samsung", 10, 0);
        Product product1 = new Product("xiaomi", 20, 30);
        Product product2 = new Product("lg", 20, 30);
        HashMap<HashSumFourChar, Product> hashMap = new HashMap<>();
        hashMap.put(new HashSumFourChar("wety"),product);
        hashMap.put(new HashSumFourChar("2rrr"),product);
        hashMap.put(new HashSumFourChar("3amm"),product1);
        hashMap.put(new HashSumFourChar("0wfd"),product2);
        Iterator<Map.Entry<HashSumFourChar, Product>> itr = hashMap.entrySet().iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}