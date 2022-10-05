package com.example.electronicshop.products;

import java.util.List;

public class ProductDataDTO {
    private int minPrice;
    private int dbMinPrice;
    private int maxPrice;
    private int dbMaxPrice;
    private int productQuantity;
    private List<String> brandList;
    private List<CategoryDTO> categoryDTOList;
    private List<Product> productList;
    private ProductFilter productFilter;

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        if (minPrice == 0) {
            this.minPrice = dbMinPrice;
        } else {
            this.minPrice = minPrice;
        }
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        if (maxPrice >= minPrice) {
            this.maxPrice = maxPrice;
        } else {
            this.maxPrice =dbMaxPrice;
        }
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public List<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    public List<CategoryDTO> getCategoryDTOList() {
        return categoryDTOList;
    }

    public void setCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        this.categoryDTOList = categoryDTOList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductFilter getProductFilter() {
        return productFilter;
    }

    public void setProductFilter(ProductFilter productFilter) {
        this.productFilter = productFilter;
    }

    public void setDbMinPrice(int dbMinPrice) {
        this.dbMinPrice = dbMinPrice;
    }

    public void setDbMaxPrice(int dbMaxPrice) {
        this.dbMaxPrice = dbMaxPrice;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "minPrice=" + minPrice +
                ", dbMinPrice=" + dbMinPrice +
                ", maxPrice=" + maxPrice +
                ", dbMaxPrice=" + dbMaxPrice +
                ", productQuantity=" + productQuantity +
                ", brandList=" + brandList +
                ", categoryDTOList=" + categoryDTOList +
                ", productList=" + productList +
                ", productFilter=" + productFilter +
                '}';
    }
}
