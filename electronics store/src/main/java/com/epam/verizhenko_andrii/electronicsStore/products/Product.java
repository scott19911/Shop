package com.epam.verizhenko_andrii.electronicsStore.products;

import java.io.Serializable;
import java.util.Locale;

public class Product implements Serializable {
    private String brand;
    private double power;
    private double price;

    public Product() {
    }

    public Product(String brand, double power, double price) {
        this.brand = brand;
        this.power = power;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (Double.compare(product.getPower(), getPower()) != 0) return false;
        if (Double.compare(product.getPrice(), getPrice()) != 0) return false;
        return getBrand() != null ? getBrand().equals(product.getBrand()) : product.getBrand() == null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand=" + brand  +
                ", power=" + power +
                ", price=" + price +
                '}';
    }

}
