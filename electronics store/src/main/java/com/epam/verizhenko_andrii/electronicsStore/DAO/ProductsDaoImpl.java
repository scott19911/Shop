package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductsDaoImpl implements ProductsDao {
    private Map<Product, Integer> productsMap;

    public ProductsDaoImpl() {
        productsMap = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return productsMap;
    }

    public void setProducts(Map<Product, Integer> productsMap) {
        this.productsMap = productsMap;
    }
    public void updateProducts(Product product, int quantity){
        productsMap.put(product,quantity);
    }
    public Product getProduct(String brand){
        for (Map.Entry<Product, Integer> productIntegerEntry : productsMap.entrySet()) {
            if (productIntegerEntry.getKey().getBrand().toLowerCase().equals(brand)) {
                return productIntegerEntry.getKey();
            }
        }
        return null;
    }

    @Override
    public int getQuantity(Product product) {
        return productsMap.get(product);
    }

}
