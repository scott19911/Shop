package com.example.electronicshop.order;

public class DeliveryNP {
    private String street;
    private String house;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public DeliveryNP(int department) {
        setStreetAndHouse(department);
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    void setStreetAndHouse(int department){
        switch (department){
            case 1: {
                street = "Poltawa Way Street";
                house = "130";
            }
            break;
            case 2: {
                street = "Prospekt Haharina";
                house = "38a";
            }
            break;
            case 3:{
                street = "Sumska";
                house = "94/95";
            }
            break;
            default: {
                street = "Pol'ova";
                house = "67";
            }
        }
    }

}
