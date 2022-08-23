package proxy;

import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapProxyTest {
    @Test
    void shouldСhangeBrand_onSet_andReturn_onGet(){
        String expBrand = "lg";
        String expBrand1 = "sam";
        Product productAble = (Product) MapProxy.newInstance(new ProductImpl());
        productAble.setBrand(expBrand);
        assertEquals(expBrand,productAble.getBrand());
        productAble.setBrand(expBrand1);
        assertEquals(expBrand1,productAble.getBrand());
    }

}