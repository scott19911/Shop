package com.example.electronicshop.dao;

import com.example.electronicshop.order.Order;
import com.example.electronicshop.order.OrderDetailsDTO;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;



public class OrderRepositoryImpl implements OrderRepository {
    private Connection connection;
    public static final String SELECT_SHIPPERS = "SELECT deliveryID, type from delivery;";
    public static final String SELECT_PAYMENTS = "SELECT paymentId, type from payment;";
    public static final String SELECT_PRODUCT_QUANTITY = "SELECT quantity from products where idproducts=?;";
    public static final String UPDATE_PRODUCT_QUANTITY = "UPDATE products set quantity=? where idproducts=?;";
    public static final String INSERT_ORDER = "INSERT INTO " +
            "`order`(customerID,orderDate,ShipperId,paymentId,receiverId,statusId) values (?,?,?,?,?,?);";
    public static final String INSERT_RECEIVER = "INSERT INTO `receiver`(userId,First_Name,surname,Phone,City,streat,apartment) " +
            "values (?,?,?,?,?,?,?);";
    public static final String INSERT_ORDER_DETAILS = "INSERT INTO orderdetails (orderID, ProductID, Quantity, current_price)" +
            " values (?,?,?,?);";
    public static final String DELIVERY_ID = "deliveryID";
    public static final String PAYMENT_ID = "paymentId";
    public static final String PAYMENT_TYPE = "type";
    public static final String DELIVERY_TYPE = "type";
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT);

    public OrderRepositoryImpl() {
    }

    @Override
    public Map<Integer, String> getShippers() {
        return getIntegerStringMap(SELECT_SHIPPERS, DELIVERY_ID, DELIVERY_TYPE);
    }

    @Override
    public Map<Integer, String> getPayments() {
        return getIntegerStringMap(SELECT_PAYMENTS, PAYMENT_ID, PAYMENT_TYPE);
    }

    @Override
    public int insertOrder(Order order) {
        connection = ConnectionPool.getConnectionThreadLocal().get();
        try (PreparedStatement stm = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1,order.getUserId());
            stm.setTimestamp(2, Timestamp.valueOf(DATE_FORMAT.format(order.getDate())));
            stm.setInt(3,order.getDeliveryId());
            stm.setInt(4,order.getPaymentId());
            stm.setInt(5,order.getReceiverId());
            stm.setInt(6,order.getStatusId());
            stm.executeUpdate();
            ResultSet resultSet = stm.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

    @Override
    public int insertRecipient(OrderDetailsDTO orderDTO, int userId) {
        connection = ConnectionPool.getConnectionThreadLocal().get();
        try (PreparedStatement stm = connection.prepareStatement(INSERT_RECEIVER, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1,userId);
            stm.setString(2,orderDTO.getRecipientName());
            stm.setString(3,orderDTO.getRecipientSurname());
            stm.setString(4,orderDTO.getRecipientPhone());
            stm.setString(5,orderDTO.getCity());
            stm.setString(6,orderDTO.getStreet());
            stm.setString(7,orderDTO.getHouse());
            stm.executeUpdate();
            ResultSet resultSet = stm.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

    @Override
    public boolean insertOrderDetails(Order order) {
        connection = ConnectionPool.getConnectionThreadLocal().get();
        try (PreparedStatement stm = connection.prepareStatement(INSERT_ORDER_DETAILS)) {
            for (Entry<Product,Integer> entry: order.getCart().entrySet()
                 ) {
                stm.setInt(1,order.getOrderId());
                stm.setInt(2,entry.getKey().getProductId());
                stm.setInt(3,entry.getValue());
                stm.setDouble(4,entry.getKey().getPrice());
                stm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Map<Integer, String> getIntegerStringMap(String selectQuery, String idName, String typeName) {
        Map<Integer, String> map = new HashMap<>();
        connection = ConnectionPool.getConnectionThreadLocal().get();
        try (PreparedStatement stm = connection.prepareStatement(selectQuery)) {
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            while (resultSet.next()) {
                map.put(resultSet.getInt(idName), resultSet.getString(typeName));
            }
            return map;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int getAvailableQuantity(int productId) {
        connection = ConnectionPool.getConnectionThreadLocal().get();
        try (PreparedStatement stm = connection.prepareStatement(SELECT_PRODUCT_QUANTITY)) {
            stm.setInt(1,productId);
            stm.executeQuery();
            ResultSet resultSet = stm.getResultSet();
            if (resultSet.next()){
                return resultSet.getInt("quantity");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

    @Override
    public void updateAvailableQuantity(int productId, int quantity) {
        connection = ConnectionPool.getConnectionThreadLocal().get();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_PRODUCT_QUANTITY)) {
            stm.setInt(1,quantity);
            stm.setInt(2,productId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
