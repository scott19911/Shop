package com.epam.verizhenko_andrii.electronicsStore.products;

import java.util.Objects;

/**
 * representation of the heir to the class of large household appliances
 *
 * @author Verizhenko
 * @since 08-01-2022
 */
public class Refregerators extends MediumDigitalAppliances {
    @Reflectable(value = "type")
    private String type;
    @Reflectable(value = "freezer")
    private double freezerVolume;
    @Reflectable(value = "refrigerator")
    private double refrigeratorVolume;

    public Refregerators() {
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
