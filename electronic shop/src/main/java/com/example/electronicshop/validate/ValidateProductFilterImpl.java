package com.example.electronicshop.validate;

import com.example.electronicshop.products.ProductsFilterDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

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
        for (Entry<String, String[]> stringEntry : request.getParameterMap().entrySet()) {
            if (stringEntry.getValue() != null) {
                productsDTO = inputData(stringEntry.getKey(), stringEntry.getValue(),productsDTO);
            }
        }
        return productsDTO;
    }

    public ProductsFilterDTO inputData(String key, String[] stringEntry, ProductsFilterDTO productsDTO) {
        switch (key) {
            case PRODUCT_NAME:
                productsDTO.setProductName(stringEntry[0]);
                break;
            case BRAND: {
                List<String> brandList = new ArrayList<>(Arrays.asList(stringEntry));
                productsDTO.setBrand(brandList);
            }
            break;
            case MAX_PRICE: {
                try {
                    productsDTO.setMaxPrice(Integer.parseInt(stringEntry[0]));
                } catch (NumberFormatException ex) {
                    productsDTO.setMaxPrice(0);
                }
            }
            break;
            case MIN_PRICE: {
                try {
                    productsDTO.setMinPrice(Integer.parseInt(stringEntry[0]));
                } catch (NumberFormatException ex) {
                    productsDTO.setMinPrice(0);
                }
            }
            break;
            case PAGE_NUMBER: {
                try {
                    productsDTO.setPageNumber(Integer.parseInt(stringEntry[0]));
                } catch (NumberFormatException ex) {
                    productsDTO.setPageNumber(0);
                }
            }
            break;
            case CATEGORY: {
                List<Integer> categoryList = new ArrayList<>();
                for (String s : stringEntry) {
                    try {
                        categoryList.add(Integer.parseInt(s));
                    } catch (NumberFormatException exception) {
                        exception.printStackTrace();
                    }
                }
                productsDTO.setCategory(categoryList);
            }
            break;
            case PAGE_SIZE: {
                try {
                    productsDTO.setPageSize(Integer.parseInt(stringEntry[0]));
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
            break;
            case PAGE_ORDER: {
                try {
                    productsDTO.setOrder(stringEntry[0]);
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
            break;
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
        if (productsDTO.getOrder() != null) {
            boolean validOrder = productsDTO.getOrder().equals(ORDER_ASC) || productsDTO.getOrder().equals(ORDER_DESC);
            if (!validOrder) {
                productsDTO.setOrder(null);
            }
        }
        return productsDTO;
    }
}
