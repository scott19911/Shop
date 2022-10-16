package com.example.electronicshop.validate;

import com.example.electronicshop.order.OrderDetailsDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface ValidateOrder {
    OrderDetailsDTO readRequest(HttpServletRequest request);
    Map<String,String> validate(OrderDetailsDTO orderDetails);
}
