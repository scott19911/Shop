package com.example.electronicshop.order;

import com.example.electronicshop.products.Product;

import java.util.List;
import java.util.Map;

public class UserOrders {
   private List<OrderInfo> orderInfoList;
   private Map<String, Map<Product, Integer>> orderProduct;
   private Map<Integer,String> statusMap;

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public Map<Integer, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Integer, String> statusMap) {
        this.statusMap = statusMap;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public Map<String, Map<Product, Integer>> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Map<String, Map<Product, Integer>> orderProduct) {
        this.orderProduct = orderProduct;
    }
}
