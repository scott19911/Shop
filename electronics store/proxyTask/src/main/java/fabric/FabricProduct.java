package fabric;

import products.Product;
import products.ProductImpl;
import proxy.FabricProxy;
import proxy.MapProxy;

public class FabricProduct {
    /**
     *  creates a product based on an input parameter
     * @param productsType - enum of products
     * @return - instance product
     */
    public Product createProduct(ProductsType productsType) {
        switch (productsType) {
            case UNMODIFIER:
                return (Product) FabricProxy.newInstance(new ProductImpl());
            case MAP_REALISATION:
                return (Product) MapProxy.newInstance(new ProductImpl());
            case SIMPLE_PRODUCT:
                return new ProductImpl();
            default:
                return null;
        }
    }
}
