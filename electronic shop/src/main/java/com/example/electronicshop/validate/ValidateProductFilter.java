package com.example.electronicshop.validate;

import com.example.electronicshop.products.ProductsFilterDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface ValidateProductFilter {
    /**
     * read all form fields from request
     *
     * @param request - incoming request with registration form data
     * @return - read data as DTO class
     */
    ProductsFilterDTO readRequest(HttpServletRequest request);

    /**
     * validation of entered data
     *
     * @param productsDTO - entered data
     * @return - valid data
     */
    ProductsFilterDTO validate(ProductsFilterDTO productsDTO);
}
