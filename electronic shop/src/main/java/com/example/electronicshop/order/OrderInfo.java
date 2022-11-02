package com.example.electronicshop.order;


import java.util.Date;


public class OrderInfo {
    private int orderNumber;
    private Date orderDate;
    private String orderStatus;
    private String statusDescription;
    private String deliveryType;
    private double totalPrice;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }



    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", statusDescription='" + statusDescription + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                '}';
    }
}
