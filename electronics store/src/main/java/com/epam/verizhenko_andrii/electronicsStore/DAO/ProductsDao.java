package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;

/**
 * A.java
 * Interface class that has the following methods.
 *
 * @author Verizhenko
 * @since 08-14-2022
 */
public interface ProductsDao {
    /**
     *Getter products with quantity
     * @return - map product,integer
     */
    Map<Product, Integer> getProducts();

    /**
     * Setter products with quantity
     * @param productsMap - set parameters
     */
    void setProducts(Map<Product, Integer> productsMap);
    /**
     * Change product quantity
     * @param product - variable product
     * @param quantity - new quantity
     */
    void updateProducts(Product product, int quantity);
    /**
     *Getter products by brand
     * @return - product
     */
    Product getProduct(String brand);
    /**
     *Getter quantity products
     * @return - quantity
     */
    int getQuantity(Product product);
}
