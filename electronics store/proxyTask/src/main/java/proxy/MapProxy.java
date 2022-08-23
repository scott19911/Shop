package proxy;

import products.Product;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
/**
 * creates a product object proxy
 * with conversion of get/set methods to put/get map methods
 */
public class MapProxy implements InvocationHandler {
    private final Product product;
    private static final int START_INDEX = 2;
    private static final String SET = "set";
    private static final String GET = "get";
    private final Map<String, Object> stringObjectMap = new HashMap<>();

    public MapProxy(Product obj) {
        this.product = obj;
    }

    public static Object newInstance(Product obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new MapProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith(GET)) {
            return stringObjectMap.get(method.getName().substring(START_INDEX));
        }
        if (method.getName().startsWith(SET)) {
            method.invoke(product,args);
            stringObjectMap.put(method.getName().substring(START_INDEX), args[0]);
        }
        return method.invoke(product,args);
    }

}
