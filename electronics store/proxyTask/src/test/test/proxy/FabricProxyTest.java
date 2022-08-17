package proxy;

import org.junit.jupiter.api.Test;
import products.ProductAble;
import products.ProductImpl;

import static org.junit.jupiter.api.Assertions.*;

class FabricProxyTest {
    @Test
    void testSetMethods(){
        ProductImpl product = new ProductImpl();
        product.setBrand("lg");
        ProductAble productAble = (ProductAble) FabricProxy.newInstance(product);
        assertEquals(product.getBrand(),productAble.getBrand());
        assertThrows(UnsupportedOperationException.class,()->productAble.setBrand("dd"));
    }

}