package com.example.electronicshop.products;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class ReadProductRequestImpl implements ReadProductRequest {
    public static final String MIN_PRICE = "minPrice";
    public static final String MAX_PRICE = "maxPrice";
    public static final String CATEGORY = "category";
    public static final String PRODUCT_NAME = "productName";
    public static final String BRAND = "brand";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_ORDER = "orderBy";
    @Override
    public ProductsFilterDTO readRequest(HttpServletRequest request) {
        Map<String,String[]> requestMap =  request.getParameterMap();
        return ProductsFilterDTO.newBuilder().priceFrom(request.getParameter(MIN_PRICE))
                .priceTo(request.getParameter(MAX_PRICE)).name(request.getParameter(PRODUCT_NAME))
                .brand(requestMap.get(BRAND)).category(requestMap.get(CATEGORY))
                .pageNumber(request.getParameter(PAGE_NUMBER)).pageSize(request.getParameter(PAGE_SIZE))
                .orderBy(request.getParameter(PAGE_ORDER)).build();
    }
}
