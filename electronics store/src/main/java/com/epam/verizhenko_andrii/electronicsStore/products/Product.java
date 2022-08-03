package com.epam.verizhenko_andrii.electronicsStore.products;

import java.util.Locale;

public class Product {
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
    public boolean filtr(String param, String value){

        if (param.toLowerCase(Locale.ROOT).equals("brand") && value == null && this.getBrand() == null){
            return true;
        }
        if(getBrand() != null && param.toLowerCase(Locale.ROOT).equals("brand") && this.getBrand().equals(value)){
            return true;
        } else if (param.equalsIgnoreCase("power") || param.equalsIgnoreCase("price")){
            try {
                double val = Double.parseDouble(value);
                if (param.toLowerCase(Locale.ROOT).equals("power") && this.getPower() == val) {
                    return true;
                }
                if (param.toLowerCase(Locale.ROOT).equals("price") && this.getPrice() == val) {
                    return true;
                }
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }
}
