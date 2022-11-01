package com.example.electronicshop.cart;

import com.example.electronicshop.dao.CartRepository;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.products.Product;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import static com.example.electronicshop.servlets.CartServlets.CART_INFO;


public class CartServiceImpl implements CartService {
    private Map<Product, Integer> cart;
    private final CartRepository cartRepository;
    private final TransactionManager transactionManager;
    public static final String ID = "id";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";

    public CartServiceImpl(CartRepository cartRepository, TransactionManager transactionManager) {
        this.cartRepository = cartRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }


    @Override
    public int totalQuantity(Map<Product, Integer> cart) {
        int quantity = 0;
        for (Entry<Product, Integer> entry : cart.entrySet()
        ) {
            quantity += entry.getValue();
        }
        return quantity;
    }

    @Override
    public double cartPrice(Map<Product, Integer> cart) {
        double totalPrice = 0;
        for (Entry<Product, Integer> entry : cart.entrySet()
        ) {
            totalPrice += (entry.getKey().getPrice() * entry.getValue());
        }
        return totalPrice;
    }

    @Override
    public Map<Product, Integer> updateCart(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<Product, Integer> cart = null;
        String[] stringsID = parameterMap.get(ID);
        String[] prices = parameterMap.get(PRICE);
        String[] stringsQuantity = parameterMap.get(QUANTITY);
        if (request.getSession(false).getAttribute(CART_INFO) != null) {
            CartInfo cartInfo = (CartInfo) request.getSession(false).getAttribute(CART_INFO);
            cart = cartInfo.getCart();
        }
        if (cart == null || cart.isEmpty()) {
            cart = new HashMap<>();
        }
        Map<Product, Integer> tempCart = cart;
        cart = transactionManager.doInTransaction(() -> {
            for (int i = 0; i < stringsID.length; i++) {
                try {
                    int id = Integer.parseInt(stringsID[i]);
                    double price = Double.parseDouble(prices[i]);
                    Product productStable = cartRepository.getProductByIdAndPrice(id, price);
                    if (productStable != null) {
                        updateStableProductQuantity(tempCart, Integer.parseInt(stringsQuantity[i]), productStable);
                    } else {
                        Product product = cartRepository.getProductById(id);
                        product.setPrice(price);
                        decreaseUnstableProductQuantity(tempCart, Integer.parseInt(stringsQuantity[i]), product);
                    }
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                }
            }
            return tempCart;
        }, Connection.TRANSACTION_READ_COMMITTED);
        if (cart != null && cart.isEmpty()) {
            request.getSession(false).removeAttribute(CART_INFO);
        }
        return cart;
    }

    public void updateStableProductQuantity(Map<Product, Integer> tempCart, int quantity, Product productStable) {
        if (quantity > 0) {
            tempCart.put(productStable, quantity);
        }
        if (quantity < 1) {
            tempCart.remove(productStable);
        }
    }

    public void decreaseUnstableProductQuantity(Map<Product, Integer> tempCart, int quantity, Product product) {
        if (tempCart.get(product) > quantity) {
            tempCart.put(product, quantity);
        }
        if (quantity < 1) {
            tempCart.remove(product);
        }
    }

    @Override
    public void clearCart(HttpServletRequest request) {
        request.getSession(false).removeAttribute(CART_INFO);
        setCart(new HashMap<>());
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public CartInfo getCartInfo(Map<Product, Integer> cart) {
        CartInfo cartInfo = new CartInfo();
        if (cart == null || cart.isEmpty()) {
            return cartInfo;
        }
        cartInfo.setTotalPrice(cartPrice(cart));
        cartInfo.setTotalQuantity(totalQuantity(cart));
        cartInfo.setCart(cart);
        return cartInfo;
    }

}
