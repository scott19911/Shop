package com.epam.verizhenko_andrii.electronicsStore.products;

/**
 * representation of the heir to the class of product
 *
 * @author Verizhenko
 * @since 08-01-2022
 */
public class MediumDigitalAppliances extends Product {
    @Reflectable(value = "height")
    private double height;
    @Reflectable(value = "weight")
    private double weight;
    @Reflectable(value = "width")
    private double width;

    public MediumDigitalAppliances() {
    }
    /**
     * Getter for Height of product
     * @return - double value
     */
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    /**
     * Getter for Weight of product
     * @return - double value
     */
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * Getter for Width of product
     * @return - double value
     */
    public double getWidth() {
        return width;
    }

    public void setWidth(double whidth) {
        this.width = whidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediumDigitalAppliances)) return false;
        MediumDigitalAppliances mda = (MediumDigitalAppliances) o;
        if (Double.compare(mda.getHeight(), getHeight()) != 0) return false;
        if (Double.compare(mda.getWeight(), getWeight()) != 0) return false;
        return Double.compare(mda.getWidth(), getWidth()) == 0;
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
