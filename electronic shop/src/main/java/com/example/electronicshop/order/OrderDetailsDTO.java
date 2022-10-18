package com.example.electronicshop.order;


import com.example.electronicshop.products.Product;

import java.util.Map;

public class OrderDetailsDTO {
    private String city;
    private String recipientName;
    private String recipientSurname;
    private String street;
    private String recipientPhone;
    private String house;
    private Map<Product, Integer> orderCart;
    private String cardNumber;
    private String dataCard;
    private String cvv2;
    private String department;
    private int delivery;
    private int payment;
    private double totalPrice;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientSurname() {
        return recipientSurname;
    }

    public void setRecipientSurname(String recipientSurname) {
        this.recipientSurname = recipientSurname;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public String getDepartment() {
        return department;
    }

    public int getDelivery() {
        return delivery;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Map<Product, Integer> getOrderCart() {
        return orderCart;
    }

    public void setOrderCart(Map<Product, Integer> orderCart) {
        this.orderCart = orderCart;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDataCard() {
        return dataCard;
    }

    public void setDataCard(String dataCard) {
        this.dataCard = dataCard;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public static OrderDetailsDTOBuilder newBuilder() {
        return new OrderDetailsDTO().new OrderDetailsDTOBuilder();
    }

    public class OrderDetailsDTOBuilder {

        public OrderDetailsDTOBuilder setCity(String city) {
            if (city != null && !city.isBlank()) {
                OrderDetailsDTO.this.city = city;
            }
            return this;
        }

        public OrderDetailsDTOBuilder setRecipientName(String recipientName) {
            if (recipientName != null && !recipientName.isBlank()) {
                OrderDetailsDTO.this.recipientName = recipientName;
            }
            return this;
        }

        public OrderDetailsDTOBuilder setRecipientSurname(String recipientSurname) {
            if (recipientSurname != null && !recipientSurname.isBlank()) {
                OrderDetailsDTO.this.recipientSurname = recipientSurname;
            }
            return this;
        }

        public OrderDetailsDTOBuilder setStreet(String street) {
            if (street != null && !street.isBlank()) {
                OrderDetailsDTO.this.street = street;
            }
            return this;
        }

        public OrderDetailsDTOBuilder setPhone(String phone) {
            if (phone != null && !phone.isBlank()) {
                OrderDetailsDTO.this.recipientPhone = phone;
            }
            return this;
        }

        public OrderDetailsDTOBuilder setHouse(String house) {
            if (house != null && !house.isBlank()) {
                OrderDetailsDTO.this.house = house;
            }
            return this;
        }

        public OrderDetailsDTOBuilder setCart(Map<Product, Integer> cart) {
            if (cart != null && !cart.isEmpty()) {
                OrderDetailsDTO.this.orderCart = cart;
            }
            return this;
        }

        public OrderDetailsDTO build() {
            return OrderDetailsDTO.this;
        }
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "city='" + city + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", recipientSurname='" + recipientSurname + '\'' +
                ", street='" + street + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", house='" + house + '\'' +
                ", orderCart=" + orderCart +
                ", cardNumber='" + cardNumber + '\'' +
                ", dataCard='" + dataCard + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", department='" + department + '\'' +
                ", delivery=" + delivery +
                ", payment=" + payment +
                '}';
    }

    public Order getOrder(){
        Order order = new Order();
        order.setDeliveryId(delivery);
        order.setPaymentId(payment);
        order.setCart(orderCart);
        order.setTotalPrice(totalPrice);
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailsDTO)) return false;

        OrderDetailsDTO that = (OrderDetailsDTO) o;

        if (getDelivery() != that.getDelivery()) return false;
        if (getPayment() != that.getPayment()) return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        if (getRecipientName() != null ? !getRecipientName().equals(that.getRecipientName()) : that.getRecipientName() != null)
            return false;
        if (getRecipientSurname() != null ? !getRecipientSurname().equals(that.getRecipientSurname()) : that.getRecipientSurname() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(that.getStreet()) : that.getStreet() != null) return false;
        if (getRecipientPhone() != null ? !getRecipientPhone().equals(that.getRecipientPhone()) : that.getRecipientPhone() != null)
            return false;
        if (getHouse() != null ? !getHouse().equals(that.getHouse()) : that.getHouse() != null) return false;
        if (getOrderCart() != null ? !getOrderCart().equals(that.getOrderCart()) : that.getOrderCart() != null)
            return false;
        if (getCardNumber() != null ? !getCardNumber().equals(that.getCardNumber()) : that.getCardNumber() != null)
            return false;
        if (getDataCard() != null ? !getDataCard().equals(that.getDataCard()) : that.getDataCard() != null)
            return false;
        if (getCvv2() != null ? !getCvv2().equals(that.getCvv2()) : that.getCvv2() != null) return false;
        return getDepartment() != null ? getDepartment().equals(that.getDepartment()) : that.getDepartment() == null;
    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getRecipientName() != null ? getRecipientName().hashCode() : 0);
        result = 31 * result + (getRecipientSurname() != null ? getRecipientSurname().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getRecipientPhone() != null ? getRecipientPhone().hashCode() : 0);
        result = 31 * result + (getHouse() != null ? getHouse().hashCode() : 0);
        result = 31 * result + (getOrderCart() != null ? getOrderCart().hashCode() : 0);
        result = 31 * result + (getCardNumber() != null ? getCardNumber().hashCode() : 0);
        result = 31 * result + (getDataCard() != null ? getDataCard().hashCode() : 0);
        result = 31 * result + (getCvv2() != null ? getCvv2().hashCode() : 0);
        result = 31 * result + (getDepartment() != null ? getDepartment().hashCode() : 0);
        result = 31 * result + getDelivery();
        result = 31 * result + getPayment();
        return result;
    }
}
