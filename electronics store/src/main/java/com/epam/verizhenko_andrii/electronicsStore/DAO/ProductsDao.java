package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;
/**
 *
 * A.java
 * Interface class that has the following methods.
 *
 * @author Verizhenko
 * @since 08-14-2022
 */
public interface ProductsDao {
    Map<Product, Integer> getProducts();

    void setProducts(Map<Product, Integer> productsMap);

    void updateProducts(Product product, int quantity);

    Product getProduct(String brand);

    int getQuantity(Product product);
}
