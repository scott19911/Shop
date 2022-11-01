package com.example.electronicshop.dao;

import com.example.electronicshop.products.Product;
import com.example.electronicshop.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRepositoryImpl implements CartRepository {

    private Connection connection;

    public static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE idproducts=?;";
    public static final String SELECT_PRODUCT_BY_ID_AND_PRICE = "SELECT * FROM products WHERE idproducts=? AND price=?;";

    @Override
    public Product getProductById(int id) {
        ConverterResultSet converterResultSet = new ConverterResultSet();
        connection = ConnectionPool.getConnectionThreadLocal().get();
        Product product = new Product();
        try (PreparedStatement stm = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            stm.setInt(1,id);
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            if (resultSet.next()) {
                converterResultSet.getProduct(product, resultSet);
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductByIdAndPrice(int id, double price) {
        ConverterResultSet converterResultSet = new ConverterResultSet();
        connection = ConnectionPool.getConnectionThreadLocal().get();
        Product product = new Product();
        try (PreparedStatement stm = connection.prepareStatement(SELECT_PRODUCT_BY_ID_AND_PRICE)) {
            stm.setInt(1,id);
            stm.setDouble(2,price);
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            if (resultSet.next()) {
                converterResultSet.getProduct(product, resultSet);
                return product;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
