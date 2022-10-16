package com.example.electronicshop.products;


import java.util.ArrayList;
import java.util.List;

import static com.example.electronicshop.dao.ProductRepositoryImpl.ORDER_ASC;
import static com.example.electronicshop.dao.ProductRepositoryImpl.ORDER_DESC;

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
        return "ProductsFilterDTO{" +
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

    public static FilterBuilder newBuilder() {
        return new ProductsFilterDTO().new FilterBuilder();
    }

    public class FilterBuilder {
        public FilterBuilder priceFrom(String fromPrice) {
            if (fromPrice != null) {
                try {
                    ProductsFilterDTO.this.minPrice = Math.max(Integer.parseInt(fromPrice), 0);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
            return this;
        }

        public FilterBuilder priceTo(String toPrice) {
            if (toPrice != null) {
                try {
                    ProductsFilterDTO.this.maxPrice = Math.max(Integer.parseInt(toPrice), 0);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
            return this;
        }

        public FilterBuilder brand(String[] brands) {
            if(brands != null) {
                List<String> brandList = new ArrayList<>();
                for (String brand : brands
                ) {
                    if (brand != null && !brand.isBlank()) {
                        brandList.add(brand);
                    }
                }
                ProductsFilterDTO.this.brand = brandList.size() > 0 ? brandList : null;
            }
            return this;
        }

        public FilterBuilder name(String name) {
            if (name != null && !name.isBlank()) {
                ProductsFilterDTO.this.productName = name;
            }
            return this;
        }

        public FilterBuilder category(String[] category) {
            if (category != null) {
                List<Integer> categoryList = new ArrayList<>();
                for (String s : category) {
                    try {
                        if (s != null) {
                            categoryList.add(Integer.parseInt(s));
                        }
                    } catch (NumberFormatException exception) {
                        exception.printStackTrace();
                    }
                }
                ProductsFilterDTO.this.category = categoryList.size() > 0 ? categoryList : null;
            }
            return this;
        }

        public FilterBuilder orderBy(String order) {
            if (order != null && order.equalsIgnoreCase(ORDER_ASC)) {
                ProductsFilterDTO.this.order = order;
            } else {
                ProductsFilterDTO.this.order = ORDER_DESC;
            }
            return this;
        }

        public FilterBuilder pageSize(String size) {
            if (size != null) {
                try {
                    ProductsFilterDTO.this.pageSize = Math.max(Integer.parseInt(size), 1);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            } else {
                ProductsFilterDTO.this.pageSize = 3;
            }
            return this;
        }

        public FilterBuilder pageNumber(String pageNumber) {
            if (pageNumber != null) {
                try {
                    ProductsFilterDTO.this.pageNumber = Math.max(Integer.parseInt(pageNumber), 0);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
            return this;
        }

        public ProductsFilterDTO build() {
            return ProductsFilterDTO.this;
        }
    }
}
