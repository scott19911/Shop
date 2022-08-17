package fabric;

import products.ProductAble;
import products.ProductImpl;
import proxy.FabricProxy;
import proxy.MapProxy;

public class FabricProduct {
    public ProductAble createProduct(ProductsType e){
        switch (e){
            case UNMOD : return (ProductAble) FabricProxy.newInstance(new ProductImpl());
            case MAP: return (ProductAble) MapProxy.newInstance(new ProductImpl());
            case SIMPLE_PRODUCT: return new ProductImpl();
        }
        return null;
    }
}
