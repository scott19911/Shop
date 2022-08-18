package fabric;

import products.Product;
import products.ProductImpl;
import proxy.FabricProxy;
import proxy.MapProxy;

public class FabricProduct {
    public Product createProduct(ProductsType productsType) {
        switch (productsType) {
            case UNMOD:
                return (Product) FabricProxy.newInstance(new ProductImpl());
            case MAP:
                return (Product) MapProxy.newInstance(new ProductImpl());
            case SIMPLE_PRODUCT:
                return new ProductImpl();
            default:
                return null;
        }
    }
}
