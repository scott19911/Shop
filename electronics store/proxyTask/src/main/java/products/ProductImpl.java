package products;

public class ProductImpl implements Product {
    private String brand;
    private double power;
    private double price;

    /**
     *allows you to get class parameters
     * @return brand of products
     */
    @Override
    public String getBrand() {
        return brand;
    }
    /**
     *allows you to set product brand
     */
    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }
    @Override
    public double getPower() {
        return power;
    }
    @Override
    public void setPower(double power) {
        this.power = power;
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
