package com.example.electronicshop.service;

import com.example.electronicshop.dao.ProductRepositoryImpl;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.products.CategoryDTO;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.products.ProductFilter;
import com.example.electronicshop.validate.ValidateProductFilter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import static com.example.electronicshop.constants.Pages.SHOP_PAGE;

public class ProductServiceImpl implements ProductService {
    public static final String SESSION_PRODUCT_FILTER = "filter";
    public static final String SESSION_PRODUCT = "product";
    public static final String SESSION_BRAND = "brand";
    public static final String SESSION_CATEGORY = "category";
    private final TransactionManager transactionManager;
    private final ProductRepositoryImpl productRepository;
    private final ValidateProductFilter validateProductFilter;

    public ProductServiceImpl(TransactionManager transactionManager, ProductRepositoryImpl productRepository,
                              ValidateProductFilter validateProductFilter) {
        this.transactionManager = transactionManager;
        this.productRepository = productRepository;
        this.validateProductFilter = validateProductFilter;
    }

    @Override
    public void getProducts(HttpServletRequest request, HttpServletResponse response) {
        List<String> brandList = transactionManager.doWithoutTransaction(productRepository::getUniqueBrand);
        List<CategoryDTO> categoryList = transactionManager.doWithoutTransaction(productRepository::getCategory);
        ProductFilter productFilter = validateProductFilter.validate(validateProductFilter.readRequest(request)).getFilter();
        List<Product> productList = transactionManager.doInTransaction(() -> {
            int pageQuantity = 0;
            if (productFilter.getPageSize() > 0) {
                pageQuantity = productRepository.countFiltered(productFilter) / productFilter.getPageSize();
                if (productRepository.countFiltered(productFilter) % productFilter.getPageSize() > 0) {
                    pageQuantity++;
                }
            }
            if (productFilter.getPageNumber() > pageQuantity) {
                productFilter.setPageNumber(0);
            }
            productFilter.setPageQuantity(pageQuantity);
            return productRepository.getFiltered(productFilter);
        }, Connection.TRANSACTION_READ_COMMITTED);
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_PRODUCT_FILTER, productFilter);
        session.setAttribute(SESSION_PRODUCT, productList);
        session.setAttribute(SESSION_BRAND, brandList);
        session.setAttribute(SESSION_CATEGORY, categoryList);
        try {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(SHOP_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
