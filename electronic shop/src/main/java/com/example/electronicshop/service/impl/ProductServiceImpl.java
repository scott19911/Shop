package com.example.electronicshop.service.impl;

import com.example.electronicshop.dao.ProductRepositoryImpl;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.products.ProductFilter;
import com.example.electronicshop.service.ProductService;
import com.example.electronicshop.validate.ValidateProductFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import static com.example.electronicshop.dao.ProductRepositoryImpl.ORDER_DESC;


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
    public Map<String,Object> getProducts(HttpServletRequest request, HttpServletResponse response) {
        ProductFilter productFilter = validateProductFilter.validate(validateProductFilter.readRequest(request)).getFilter();
        if (request.getSession(false).getAttribute(SESSION_PRODUCT_FILTER) != null){
            ProductFilter productFilterSession = (ProductFilter) request.getSession(false).getAttribute(SESSION_PRODUCT_FILTER);
            productFilter.setPageSize(productFilterSession.getPageSize());
            if (productFilter.getOrder() == null) {
                if(productFilterSession.getOrder() == null){
                    productFilter.setOrder(ORDER_DESC);
                } else {
                    productFilter.setOrder(productFilterSession.getOrder());
                }
            }
        }
        Map<String,Object> productData  = transactionManager.doInTransaction(() -> {
            Map<String,Object> productInformation = new HashMap<>();
            int pageQuantity = 0;
            productInformation.put(SESSION_BRAND,productRepository.getUniqueBrand());
            productInformation.put(SESSION_CATEGORY,productRepository.getCategory());
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
            productInformation.put(SESSION_PRODUCT, productRepository.getFiltered(productFilter));
            return productInformation;
        }, Connection.TRANSACTION_READ_COMMITTED);
        productData.put(SESSION_PRODUCT_FILTER,productFilter);
        return productData;
    }
}
