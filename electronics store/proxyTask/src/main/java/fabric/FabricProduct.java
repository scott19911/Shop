package fabric;

import products.Product;
import products.ProductImplementation;
import proxy.FabricProxy;
import proxy.MapProxy;

public class FabricProduct {
    /**
     * creates a product based on an input parameter
     *
     * @param productsType - enum of products
     * @return - instance product
     */
    public Product createProduct(ProductsType productsType) {
        switch (productsType) {
            case UNMODIFIER:
                return (Product) FabricProxy.newInstance(new ProductImplementation());
            case MAP_REALISATION:
                return (Product) MapProxy.newInstance(new ProductImplementation());
            case SIMPLE_PRODUCT:
                return new ProductImplementation();
            default:
                return null;
        }
    }
}
