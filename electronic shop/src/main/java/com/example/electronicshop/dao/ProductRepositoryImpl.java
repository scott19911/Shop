package com.example.electronicshop.dao;

import com.example.electronicshop.products.CategoryDTO;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.products.ProductDataDTO;
import com.example.electronicshop.products.ProductFilter;
import com.example.electronicshop.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Product database access class
 */
public class ProductRepositoryImpl implements ProductRepository {
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    public static final String SELECT_UNIQUE_BRAND = "SELECT DISTINCT brand FROM products";
    public static final String SELECT_CATEGORY = "SELECT * FROM category";
    public static final String PRODUCT_ID = "idproducts";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_BRAND = "brand";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_LIMIT = " LIMIT ?,?";
    public static final String PRODUCT_CATEGORY = "category";
    public static final String PRODUCT_IMAGE = "imgUrl";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String CATEGORY_ID = "idcategory";
    public static final String CATEGORY_NAME = "categoryType";
    public static final String ORDER_ASC = "ASC";
    public static final String ORDER_DESC = "DESC";
    public static final String QUANTITY = "quantity";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public ConnectionPool connectionPool;
    public static final String COUNT_PRODUCT = "SELECT COUNT(idproducts) as quantity," +
            " MIN(price) as min, MAX(price) as max FROM products ";
    public Connection connection;

    /**
     * Constructor with connection pool initialization
     *
     * @param connectionPool - db connection pool
     */
    public ProductRepositoryImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        connection = connectionPool.getConnection();
    }

    @Override
    public List<String> getUniqueBrand() {
        ConverterResultSet converterResultSet = new ConverterResultSet();
        try (PreparedStatement stm = connection.prepareStatement(SELECT_UNIQUE_BRAND)) {
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            return converterResultSet.getProductBrand(resultSet);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public List<CategoryDTO> getCategory() {
        ConverterResultSet converterResultSet = new ConverterResultSet();
        try (PreparedStatement stm = connection.prepareStatement(SELECT_CATEGORY)) {
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            return converterResultSet.getCategory(resultSet);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public List<Product> getFiltered(ProductFilter filter) {
        ConverterResultSet converterResultSet = new ConverterResultSet();
        String selectProduct = getFilterSelect(filter);
        try (PreparedStatement stm = connection.prepareStatement(selectProduct)) {
            setParameters(filter, stm, true).executeQuery();
            ResultSet resultSet = stm.getResultSet();
            return converterResultSet.getAllProduct(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDataDTO countFiltered(ProductFilter filter) {
        ProductDataDTO productData = new ProductDataDTO();
        String selectProduct = getFilterSelect(filter);
        selectProduct = selectProduct.replace(SELECT_ALL_PRODUCTS, COUNT_PRODUCT);
        selectProduct = selectProduct.replace(PRODUCT_LIMIT, "");
        try (PreparedStatement stm = connection.prepareStatement(selectProduct)) {
            setParameters(filter, stm, false).executeQuery();
            ResultSet resultSet = stm.getResultSet();
            if (resultSet.next()) {
                productData.setProductQuantity(resultSet.getInt(QUANTITY));
                productData.setDbMinPrice(resultSet.getInt(MIN));
                productData.setDbMaxPrice(resultSet.getInt(MAX));
                return productData;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productData;
    }

    /**
     * generates a query string to the db based on the filter
     *
     * @param filter - filter options
     * @return - query string
     */
    public String getFilterSelect(ProductFilter filter) {
        return new Builder().priceFrom(filter.getMinPrice()).priceTo(filter.getMaxPrice()).brand(filter.getBrand())
                .name(filter.getProductName()).category(filter.getCategory()).orderBy(filter.getOrder())
                .offsetLimit(filter.getPageSize()).build();
    }

    /**
     * fills the prepared statement with the values ​​received from the filter
     *
     * @param productFilter     - filter options values
     * @param preparedStatement - This object can then be used to efficiently execute this statement multiple times.
     * @param isFilter          - true if the method will get the filtered product or count the filtered product
     * @return - preparedStatement
     * @throws SQLException - when can't set preparedStatement
     */
    public PreparedStatement setParameters(ProductFilter productFilter, PreparedStatement preparedStatement
            , boolean isFilter) throws SQLException {
        int index = 1;
        if (productFilter.getMinPrice() > 0) {
            preparedStatement.setInt(index++, productFilter.getMinPrice());
        }
        if (productFilter.getMaxPrice() > 0) {
            preparedStatement.setInt(index++, productFilter.getMaxPrice());
        }
        if (productFilter.getBrand() != null) {
            for (int i = 0; i < productFilter.getBrand().size(); i++) {
                preparedStatement.setString(index++, productFilter.getBrand().get(i));
            }
        }
        if (productFilter.getProductName() != null) {
            preparedStatement.setString(index++, "%" + productFilter.getProductName() + "%");

        }
        if (productFilter.getCategory() != null) {
            for (int i = 0; i < productFilter.getCategory().size(); i++) {
                preparedStatement.setInt(index++, productFilter.getCategory().get(i));
            }
        }
        if (productFilter.getPageSize() > 0 && isFilter) {
            preparedStatement.setInt(index++, productFilter.getPageNumber() * productFilter.getPageSize());
            preparedStatement.setInt(index, productFilter.getPageSize());
        }
        return preparedStatement;
    }

    /**
     * Query string builder
     */
    public static class Builder {
        private final StringBuilder sqlSelect = new StringBuilder("SELECT * FROM products ");

        public Builder priceFrom(int minPrice) {
            if (minPrice > 0) {
                if (sqlSelect.toString().endsWith(" ")) {
                    sqlSelect.append("WHERE price >= ?");
                } else {
                    sqlSelect.append(" and price >= ?");
                }
            }
            return this;
        }

        public Builder priceTo(int maxPrice) {
            if (maxPrice > 0) {
                if (sqlSelect.toString().endsWith(" ")) {
                    sqlSelect.append("WHERE price <= ?");
                } else {
                    sqlSelect.append(" and price <= ?");
                }
            }
            return this;
        }

        public Builder brand(List<String> brands) {
            if (brands != null) {
                if (brands.size() == 1) {
                    if (sqlSelect.toString().endsWith(" ")) {
                        sqlSelect.append("WHERE brand = ?");
                    } else {
                        sqlSelect.append(" and brand = ?");
                    }
                } else {
                    if (sqlSelect.toString().endsWith(" ")) {
                        sqlSelect.append("WHERE brand in(?,");
                    } else {
                        sqlSelect.append(" and brand in (?,");
                    }
                }
                if (brands.size() > 1) {
                    sqlSelect.append("?,".repeat(brands.size() - 2));
                    sqlSelect.append("?)");
                }
            }
            return this;
        }

        public Builder name(String name) {
            if (name != null) {
                if (sqlSelect.toString().endsWith(" ")) {
                    sqlSelect.append("WHERE name LIKE '%?%'");
                } else {
                    sqlSelect.append(" and name LIKE ?");
                }
            }
            return this;
        }

        public Builder category(List<Integer> category) {
            if (category != null) {
                if (category.size() == 1) {
                    if (sqlSelect.toString().endsWith(" ")) {
                        sqlSelect.append("WHERE category = ?");
                    } else {
                        sqlSelect.append(" and category = ?");
                    }
                } else {
                    if (sqlSelect.toString().endsWith(" ")) {
                        sqlSelect.append("category in(?,");
                    } else {
                        sqlSelect.append(" and category in (?,");
                    }
                }
                if (category.size() > 1) {
                    sqlSelect.append("?,".repeat(category.size() - 2));
                    sqlSelect.append("?)");
                }
            }
            return this;
        }

        public Builder orderBy(String order) {
            if (order != null) {
                sqlSelect.append(" ORDER BY price ").append(order);
            }
            return this;
        }

        public Builder offsetLimit(int limit) {
            if (limit == 0) {
                return this;
            } else {
                sqlSelect.append(PRODUCT_LIMIT);
            }
            return this;
        }

        public String build() {
            return sqlSelect.toString();
        }
    }
}
