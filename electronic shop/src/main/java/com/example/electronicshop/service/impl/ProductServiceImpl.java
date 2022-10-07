package com.example.electronicshop.service.impl;

import com.example.electronicshop.dao.ProductRepositoryImpl;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.products.ProductDataDTO;
import com.example.electronicshop.products.ProductFilter;
import com.example.electronicshop.products.ReadProductRequest;
import com.example.electronicshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;


public class ProductServiceImpl implements ProductService {
    private final TransactionManager transactionManager;
    private final ProductRepositoryImpl productRepository;
    private final ReadProductRequest readProductRequest;

    public ProductServiceImpl(TransactionManager transactionManager, ProductRepositoryImpl productRepository,
                              ReadProductRequest readProductRequest) {
        this.transactionManager = transactionManager;
        this.productRepository = productRepository;
        this.readProductRequest = readProductRequest;
    }

    @Override
    public ProductDataDTO getProducts(HttpServletRequest request, HttpServletResponse response) {
        ProductFilter productFilter = readProductRequest.readRequest(request).getFilter();
        ProductDataDTO productData = transactionManager.doInTransaction(() -> {
            int pageQuantity;
            ProductDataDTO productInformation = productRepository.countFiltered(productFilter);
            int pageSize = productFilter.getPageSize() > 0 ? productFilter.getPageSize() : productInformation.getProductQuantity();
            pageQuantity = productInformation.getProductQuantity() / pageSize;
            if (productInformation.getProductQuantity() % pageSize > 0) {
                pageQuantity++;
            }
            productInformation.setBrandList(productRepository.getUniqueBrand());
            productInformation.setCategoryDTOList(productRepository.getCategory());
            productInformation.setMinPrice(productFilter.getMinPrice());
            productInformation.setMaxPrice(productFilter.getMaxPrice());
            if (productFilter.getPageNumber() > pageQuantity) {
                productFilter.setPageNumber(0);
            }
            productFilter.setPageQuantity(pageQuantity);
            productInformation.setProductList( productRepository.getFiltered(productFilter));
            return productInformation;
        }, Connection.TRANSACTION_READ_COMMITTED);
        productData.setProductFilter(productFilter);
        return productData;
    }
}
