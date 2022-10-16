package com.example.electronicshop.products;


/**
 * Product representation class
 */
public class Product implements Cloneable{
    private String name;
    private String brand;
    private String imgUrl;
    private String description;
    private int productId;
    private int category;
    private int quantity;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                ", category=" + category +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getProductId() != product.getProductId()) return false;
        if (getCategory() != product.getCategory()) return false;
        if (getQuantity() != product.getQuantity()) return false;
        if (getPrice() != product.getPrice()) return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null) return false;
        if (getBrand() != null ? !getBrand().equals(product.getBrand()) : product.getBrand() != null) return false;
        if (getImgUrl() != null ? !getImgUrl().equals(product.getImgUrl()) : product.getImgUrl() != null) return false;
        return getDescription() != null ? getDescription().equals(product.getDescription()) : product.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getImgUrl() != null ? getImgUrl().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getProductId();
        result = 31 * result + getCategory();
        result = 31 * result + getQuantity();
        result = (int) (31 * result + getPrice());
        return result;
    }
    @Override
    public Object clone() {
        Product product;
        try {
            product = (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            product = new Product();
        }
        product.setBrand(this.brand);
        product.setQuantity(this.quantity);
        product.setPrice(this.price);
        product.setProductId(this.productId);
        product.setCategory(this.category);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setImgUrl(this.imgUrl);
        return product;
    }
}
