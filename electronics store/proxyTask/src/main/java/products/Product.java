package products;

/**
 * The interface describes the main fields of the product
 */
public interface Product {
    String getBrand();
    void setBrand(String brand);
    double getPower();
    void setPower(double power);
    double getPrice();
    void setPrice(double price);
}
