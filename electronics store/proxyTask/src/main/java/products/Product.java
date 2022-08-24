package products;

/**
 * The interface describes the main fields of the product
 */
public interface Product {
    /**
     * Getter for brand of product
     * @return - string value
     */
    String getBrand();

    void setBrand(String brand);
    /**
     * Getter for power of product
     * @return - double value
     */
    double getPower();

    void setPower(double power);
    /**
     * Getter for price of product
     * @return - double value
     */
    double getPrice();

    void setPrice(double price);
}
