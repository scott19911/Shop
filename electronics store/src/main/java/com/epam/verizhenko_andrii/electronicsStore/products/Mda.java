package com.epam.verizhenko_andrii.electronicsStore.products;

public class Mda extends Product {
    private double height;
    private double weight;
    private double whidth;

    public Mda() {
    }

    public Mda(String brand,  double power, double price, double height, double weight, double whidth) {
        super(brand, power, price);
        this.height = height;
        this.weight = weight;
        this.whidth = whidth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWhidth() {
        return whidth;
    }

    public void setWhidth(double whidth) {
        this.whidth = whidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mda)) return false;

        Mda mda = (Mda) o;

        if (Double.compare(mda.getHeight(), getHeight()) != 0) return false;
        if (Double.compare(mda.getWeight(), getWeight()) != 0) return false;
        return Double.compare(mda.getWhidth(), getWhidth()) == 0;
    }

    @Override
    public String toString() {
        return "Mda{" +
                "height=" + height +
                ", weight=" + weight +
                ", whidth=" + whidth +
                '}';
    }
}
