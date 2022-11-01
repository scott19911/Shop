package com.example.electronicshop.order;

import com.example.electronicshop.products.Product;

import java.util.List;
import java.util.Map;

public class UserOrders {
   private List<OrderInfo> orderInfoList;
   private Map<String, Map<Product, Integer>> orderProduct;

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
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
