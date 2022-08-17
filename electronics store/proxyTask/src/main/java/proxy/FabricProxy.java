package proxy;


import products.ProductAble;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FabricProxy implements InvocationHandler {
    private final ProductAble productAble;

    public static Object newInstance(ProductAble obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new FabricProxy(obj));
    }

    private FabricProxy(ProductAble obj) {

        this.productAble = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith("set")){
            throw new UnsupportedOperationException("Can not set parameters");
        }
        return method.invoke(productAble, args);
    }
}
