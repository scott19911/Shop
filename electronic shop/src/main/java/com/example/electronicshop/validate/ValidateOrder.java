package com.example.electronicshop.validate;

import com.example.electronicshop.order.OrderDetails;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface ValidateOrder {
    OrderDetails readRequest(HttpServletRequest request);
    Map<String,String> validate(OrderDetails orderDetails);
}
