package com.example.electronicshop.dao;

import com.example.electronicshop.products.Product;

public interface CartRepository {
    /**
     * Allows you to get a product by its ID
     *
     * @param id - product id
     * @return - if a product with this id exists returns it
     */
    Product getProductById(int id);

    /**
     * Allows you to get a product by its ID and price
     *
     * @param id    - product id
     * @param price - product price
     * @return - if a product with this id and price exists returns it
     */
    Product getProductByIdAndPrice(int id, double price);
}
