package com.epam.verizhenko_andrii.electronicsStore.products;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * representation of the base class product
 *
 * @author Verizhenko
 * @since 08-01-2022
 */
public class Product implements Serializable {
    @Reflectable(value = "brand")
    private String brand;
    @Reflectable(value = "power")
    private double power;
    @Reflectable(value = "price")
    private double price;
    public Product() {
    }

    public Product(String brand, double power, double price) {
        this.brand = brand;
        this.power = power;
        this.price = price;
    }

    /**
     * Getter for brand of product
     * @return - string value
     */
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     * Getter for power of product
     * @return - double value
     */
    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
    /**
     * Getter for price of product
     * @return - double value
     */
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
                "brand=" + brand +
                ", power=" + power +
                ", price=" + price +
                '}';
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
    public @interface Reflectable {
        String value();
    }

}
