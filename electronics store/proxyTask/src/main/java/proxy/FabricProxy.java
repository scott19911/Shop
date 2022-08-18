package proxy;


import products.Product;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FabricProxy implements InvocationHandler {
    private static final String SET = "set";
    private final Product productAble;

    private FabricProxy(Product obj) {

        this.productAble = obj;
    }

    public static Object newInstance(Product obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new FabricProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith(SET)) {
            throw new UnsupportedOperationException("Can not set parameters");
        }
        return method.invoke(productAble, args);
    }
}
