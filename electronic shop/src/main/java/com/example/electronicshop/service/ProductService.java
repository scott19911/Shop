package com.example.electronicshop.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The class is responsible for displaying and processing the list of products
 */
public interface ProductService {
    /**
     * product list processing method
     *
     * @param request  - input parameters to get a list of products
     * @param response - servlet response
     */
    void getProducts(HttpServletRequest request, HttpServletResponse response);
}
