package proxy;

import org.junit.jupiter.api.Test;
import products.ProductAble;
import products.ProductImpl;

import static org.junit.jupiter.api.Assertions.*;

class MapProxyTest {
    @Test
    void test(){
        String expBrand = "lg";
        String expBrand1 = "sam";
        ProductAble productAble = (ProductAble) MapProxy.newInstance( new ProductImpl());
        productAble.setBrand(expBrand);
        assertEquals(expBrand,productAble.getBrand());
        productAble.setBrand(expBrand1);
        assertEquals(expBrand1,productAble.getBrand());


    }

}