package com.epam.verizhenko_andrii.electronicsStore.products;

public class Mda extends Product {
    @Reflectable(value = "height")
    private double height;
    @Reflectable(value = "weight")
    private double weight;
    @Reflectable(value = "width")
    private double width;


    public Mda() {
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
        return width;
    }

    public void setWhidth(double whidth) {
        this.width = whidth;
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
                ", whidth=" + width +
                '}';
    }
}
