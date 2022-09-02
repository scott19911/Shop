package proxy;

import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapProxyTest {
    @Test
    public void shouldChangeBrandTo_sam_whenSet_sam() {
        String expBrand = "lg";
        String expBrand1 = "sam";
        Product productAble = (Product) MapProxy.newInstance(new ProductImpl());
        productAble.setBrand(expBrand);
        assertEquals(expBrand, productAble.getBrand());
        productAble.setBrand(expBrand1);
        assertEquals(expBrand1, productAble.getBrand());
    }

}