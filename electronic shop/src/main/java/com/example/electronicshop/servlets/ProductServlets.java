package com.example.electronicshop.servlets;

import com.example.electronicshop.service.ProductService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/shop")
public class ProductServlets extends HttpServlet {
    public static final String PRODUCT_SERVICE = "ProductService";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);

        productService.getProducts(req, resp);
    }
}
