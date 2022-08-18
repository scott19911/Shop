package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;

public interface ProductsDao {
    Map<Product, Integer> getProducts();

    void setProducts(Map<Product, Integer> productsMap);

    void updateProducts(Product product, int quantity);

    Product getProduct(String brand);

    int getQuantity(Product product);
}
