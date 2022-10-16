package com.example.electronicshop.dao;

import com.example.electronicshop.order.Order;
import com.example.electronicshop.order.OrderDetailsDTO;

import java.util.Map;

public interface OrderRepository {
    Map<Integer,String> getShippers();
    Map<Integer,String> getPayments();
    int insertOrder(Order order);
    int insertRecipient(OrderDetailsDTO orderDTO, int userId);
    int getAvailableQuantity(int productId);
    void updateAvailableQuantity(int productId,int quantity);
    boolean insertOrderDetails(Order order);
}
