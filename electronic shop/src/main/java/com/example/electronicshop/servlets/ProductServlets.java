package com.example.electronicshop.servlets;

import com.example.electronicshop.products.ProductDataDTO;
import com.example.electronicshop.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.electronicshop.constants.Pages.SHOP_PAGE;

@WebServlet("/shop")
public class ProductServlets extends HttpServlet {
    public static final String PRODUCT_SERVICE = "ProductService";
    public static final String SESSION_PRODUCT_DATA = "productData";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);
        ProductDataDTO productData = productService.getProducts(req, resp);
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_PRODUCT_DATA, productData);
        try {
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher(SHOP_PAGE);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
