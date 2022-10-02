package com.example.electronicshop.products;

import java.util.List;

/**
 * Product filter representation class
 */
public class ProductFilter {
    private int minPrice;
    private int maxPrice;
    private List<Integer> category;
    private String productName;
    private List<String> brand;
    private int pageSize;
    private int pageNumber;
    private String order;
    private int pageQuantity;

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

    public int getPageQuantity() {
        return pageQuantity;
    }

    public void setPageQuantity(int pageQuantity) {
        this.pageQuantity = pageQuantity;
    }

    public List<String> getBrand() {
        return brand;
    }

    public void setBrand(List<String> brands) {
        this.brand = brands;
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

    @Override
    public String toString() {
        return "ProductFilter{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", category=" + category +
                ", productName='" + productName + '\'' +
                ", brand=" + brand +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", order='" + order + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFilter)) return false;

        ProductFilter that = (ProductFilter) o;

        if (getMinPrice() != that.getMinPrice()) return false;
        if (getMaxPrice() != that.getMaxPrice()) return false;
        if (getPageSize() != that.getPageSize()) return false;
        if (getPageNumber() != that.getPageNumber()) return false;
        if (getPageQuantity() != that.getPageQuantity()) return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getProductName() != null ? !getProductName().equals(that.getProductName()) : that.getProductName() != null)
            return false;
        if (getBrand() != null ? !getBrand().equals(that.getBrand()) : that.getBrand() != null) return false;
        return getOrder() != null ? getOrder().equals(that.getOrder()) : that.getOrder() == null;
    }

    @Override
    public int hashCode() {
        int result = getMinPrice();
        result = 31 * result + getMaxPrice();
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + getPageSize();
        result = 31 * result + getPageNumber();
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        result = 31 * result + getPageQuantity();
        return result;
    }
}
