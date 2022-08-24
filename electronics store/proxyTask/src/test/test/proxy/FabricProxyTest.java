package proxy;

import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FabricProxyTest {
    @Test
    public void shouldReturnBrand_whenGet_andThrowExeption_whenSet() {
        ProductImplementation product = new ProductImplementation();
        product.setBrand("lg");
        Product actProduct = (Product) FabricProxy.newInstance(product);
        assertEquals(product.getBrand(), actProduct.getBrand());
        assertThrows(UnsupportedOperationException.class, () -> actProduct.setBrand("dd"));
    }

}