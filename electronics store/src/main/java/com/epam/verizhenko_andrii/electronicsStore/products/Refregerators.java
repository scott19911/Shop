package com.epam.verizhenko_andrii.electronicsStore.products;

import java.util.Objects;

public class Refregerators extends Mda {
    private String type;
    private double freezerVolume;
    private double refrigeratorVolume;

    public Refregerators() {
    }

    public Refregerators(String brand, double power, double price, double height, double weight,
                         double whidth, String type, double freezerVolume, double refrigeratorVolume) {
        super(brand,  power, price, height, weight, whidth);
        this.type = type;
        this.freezerVolume = freezerVolume;
        this.refrigeratorVolume = refrigeratorVolume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFreezerVolume() {
        return freezerVolume;
    }

    public void setFreezerVolume(double freezerVolume) {
        this.freezerVolume = freezerVolume;
    }

    public double getRefrigeratorVolume() {
        return refrigeratorVolume;
    }

    public void setRefrigeratorVolume(double refrigeratorVolume) {
        this.refrigeratorVolume = refrigeratorVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Refregerators)) return false;
        Refregerators that = (Refregerators) o;
        return Double.compare(that.getFreezerVolume(),
                getFreezerVolume()) == 0 && Double.compare(that.getRefrigeratorVolume(),
                getRefrigeratorVolume()) == 0 && Objects.equals(getType(), that.getType());
    }

    @Override
    public String toString() {
        return "Refregerators{" +
                "type='" + type + '\'' +
                ", freezerМolume=" + freezerVolume +
                ", refrigeratorVolume=" + refrigeratorVolume +
                '}';
    }
}
