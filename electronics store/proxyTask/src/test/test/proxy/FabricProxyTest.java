package proxy;

import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricProxyTest {
    @Test
    void shouldReturnBrand_whenGet_andThrowExeption_whenSet(){
        ProductImpl product = new ProductImpl();
        product.setBrand("lg");
        Product actProduct = (Product) FabricProxy.newInstance(product);
        assertEquals(product.getBrand(),actProduct.getBrand());
        assertThrows(UnsupportedOperationException.class,()->actProduct.setBrand("dd"));
    }

}