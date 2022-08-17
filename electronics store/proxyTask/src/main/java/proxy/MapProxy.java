package proxy;

import products.ProductAble;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MapProxy implements InvocationHandler {
    private final ProductAble OBJ;
    private final Map<String, Object> MAP = new HashMap<>();

    public static Object newInstance(ProductAble obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new MapProxy(obj));
    }

    public MapProxy(ProductAble obj) {
        this.OBJ = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return MAP.get(method.getName().substring(2));
        }
        if (method.getName().startsWith("set")) {
            method.invoke(OBJ,args);
            MAP.put(method.getName().substring(2), args[0]);
        }
        return method.invoke(OBJ,args);
    }

}
