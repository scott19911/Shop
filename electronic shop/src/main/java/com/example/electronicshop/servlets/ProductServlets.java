package com.example.electronicshop.servlets;

import com.example.electronicshop.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.example.electronicshop.constants.Pages.SHOP_PAGE;
import static com.example.electronicshop.service.impl.ProductServiceImpl.SESSION_BRAND;
import static com.example.electronicshop.service.impl.ProductServiceImpl.SESSION_CATEGORY;
import static com.example.electronicshop.service.impl.ProductServiceImpl.SESSION_PRODUCT;
import static com.example.electronicshop.service.impl.ProductServiceImpl.SESSION_PRODUCT_FILTER;

@WebServlet("/shop")
public class ProductServlets extends HttpServlet {
    public static final String PRODUCT_SERVICE = "ProductService";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);
        Map<String, Object> productData = productService.getProducts(req, resp);
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_PRODUCT_FILTER, productData.get(SESSION_PRODUCT_FILTER));
        session.setAttribute(SESSION_PRODUCT, productData.get(SESSION_PRODUCT));
        session.setAttribute(SESSION_BRAND, productData.get(SESSION_BRAND));
        session.setAttribute(SESSION_CATEGORY, productData.get(SESSION_CATEGORY));
        try {
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher(SHOP_PAGE);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
