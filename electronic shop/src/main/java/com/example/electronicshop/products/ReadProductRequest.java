package com.example.electronicshop.products;

import jakarta.servlet.http.HttpServletRequest;

public interface ReadProductRequest {
    ProductsFilterDTO readRequest(HttpServletRequest request);
}
