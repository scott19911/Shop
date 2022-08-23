package proxy;
import products.Product;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * creates a product object proxy with non-modifiable fields
 * when trying to call a set method throws an exception
 */
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

    /**
     *
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return - the result of the called method
     * @throws UnsupportedOperationException - when trying to call a set method
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith(SET)) {
            throw new UnsupportedOperationException("Can not set parameters");
        }
        return method.invoke(productAble, args);
    }
}
