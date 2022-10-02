package com.example.electronicshop.products;


import java.util.List;

/**
 * product data transfer object
 * storage of filter parameters and allow get product filter
 */
public class ProductsFilterDTO {
    private int minPrice;
    private int maxPrice;
    private List<Integer> category;
    private String productName;
    private List<String> brand;
    private int pageSize;
    private int pageNumber;
    private String order;

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Integer> getCategory() {
        return category;
    }

    public void setCategory(List<Integer> category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getBrand() {
        return brand;
    }

    public void setBrand(List<String> brand) {
        this.brand = brand;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public ProductFilter getFilter() {
        ProductFilter productFilter = new ProductFilter();
        productFilter.setMinPrice(minPrice);
        productFilter.setMaxPrice(maxPrice);
        productFilter.setProductName(productName);
        productFilter.setBrand(brand);
        productFilter.setCategory(category);
        productFilter.setPageNumber(pageNumber);
        productFilter.setPageSize(pageSize);
        productFilter.setOrder(order);
        return productFilter;

    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", category=" + category +
                ", productName='" + productName + '\'' +
                ", brand=" + brand +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
