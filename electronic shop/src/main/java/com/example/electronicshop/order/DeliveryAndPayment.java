package com.example.electronicshop.order;

import java.util.Map;

public class DeliveryAndPayment {
    Map<Integer, String> delivery;
    Map<Integer, String> payment;

    public Map<Integer, String> getDelivery() {
        return delivery;
    }

    public void setDelivery(Map<Integer, String> delivery) {
        this.delivery = delivery;
    }

    public Map<Integer, String> getPayment() {
        return payment;
    }

    public void setPayment(Map<Integer, String> payment) {
        this.payment = payment;
    }
}
