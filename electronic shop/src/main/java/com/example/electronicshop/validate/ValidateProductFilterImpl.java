package com.example.electronicshop.validate;

import com.example.electronicshop.products.ProductsFilterDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.electronicshop.dao.ProductRepositoryImpl.ORDER_ASC;
import static com.example.electronicshop.dao.ProductRepositoryImpl.ORDER_DESC;

public class ValidateProductFilterImpl implements ValidateProductFilter {
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
        ProductsFilterDTO productsDTO = new ProductsFilterDTO();
        if (request.getParameter(PRODUCT_NAME) != null) {
            productsDTO.setProductName(request.getParameter(PRODUCT_NAME));
        }
        if (request.getParameter(BRAND) != null) {
            Map<String, String[]> paramMap = request.getParameterMap();
            List<String> brandList = new ArrayList<>(Arrays.asList(paramMap.get(BRAND)));
            productsDTO.setBrand(brandList);
        }
        if (request.getParameter(MAX_PRICE) != null) {
            try {
                productsDTO.setMaxPrice(Integer.parseInt(request.getParameter(MAX_PRICE)));
            } catch (NumberFormatException ex) {
                productsDTO.setMaxPrice(0);
            }
        }
        if (request.getParameter(MIN_PRICE) != null) {
            try {
                productsDTO.setMinPrice(Integer.parseInt(request.getParameter(MIN_PRICE)));
            } catch (NumberFormatException ex) {
                productsDTO.setMinPrice(0);
            }
        }
        if (request.getParameter(PAGE_NUMBER) != null) {
            try {
                productsDTO.setPageNumber(Integer.parseInt(request.getParameter(PAGE_NUMBER)));
            } catch (NumberFormatException ex) {
                productsDTO.setPageNumber(0);
            }
        }
        if (request.getParameter(CATEGORY) != null) {
            Map<String, String[]> paramMap = request.getParameterMap();
            String[] stringCategory = paramMap.get(CATEGORY);
            List<Integer> categoryList = new ArrayList<>();
            for (String s : stringCategory) {
                try {
                    categoryList.add(Integer.parseInt(s));
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
            productsDTO.setCategory(categoryList);
        }
        if (request.getParameter(PAGE_SIZE) != null) {
            try {
                productsDTO.setPageSize(Integer.parseInt(request.getParameter(PAGE_SIZE)));
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
        if (request.getParameter(PAGE_ORDER) != null) {
            try {
                productsDTO.setOrder(request.getParameter(PAGE_ORDER));
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
        return productsDTO;
    }

    @Override
    public ProductsFilterDTO validate(ProductsFilterDTO productsDTO) {
        if (productsDTO.getProductName() != null && productsDTO.getProductName().isBlank()) {
            productsDTO.setProductName(null);
        }
        if (productsDTO.getMinPrice() < 0) {
            productsDTO.setMinPrice(0);
        }
        if (productsDTO.getBrand() != null) {
            productsDTO.getBrand().removeIf(String::isBlank);
        }
        if (productsDTO.getMaxPrice() < 0 || productsDTO.getMinPrice() > productsDTO.getMaxPrice()) {
            productsDTO.setMaxPrice(0);
        }
        if (productsDTO.getPageNumber() < 0) {
            productsDTO.setPageNumber(0);
        }
        if (productsDTO.getPageSize() < 0) {
            productsDTO.setPageSize(0);
        }
        if (productsDTO.getOrder() == null || !productsDTO.getOrder().equalsIgnoreCase(ORDER_ASC)) {
            productsDTO.setOrder(ORDER_DESC);
        }
        return productsDTO;
    }
}
