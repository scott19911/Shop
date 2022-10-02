package com.example.electronicshop.dao;

import com.example.electronicshop.products.CategoryDTO;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.products.ProductFilter;

import java.util.List;

public interface ProductRepository {

    /**
     * allows you to get all available brands
     *
     * @return - list of product brand
     */
    List<String> getUniqueBrand();

    /**
     * allows you to get a list of categories and their IDs
     *
     * @return - list of category
     */
    List<CategoryDTO> getCategory();

    /**
     * Returns a filtered list of products
     *
     * @param filter - filter options
     * @return - filtered list of products
     */
    List<Product> getFiltered(ProductFilter filter);

    /**
     * Counts the number of filtered products
     *
     * @param filter - filter options
     * @return - products quantity
     */
    int countFiltered(ProductFilter filter);

}
