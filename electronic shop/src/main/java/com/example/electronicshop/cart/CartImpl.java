package com.example.electronicshop.cart;

import com.example.electronicshop.dao.CartRepository;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.products.Product;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.example.electronicshop.servlets.CartServlets.ADD_TO_CART;
import static com.example.electronicshop.servlets.CartServlets.CART_INFO;
import static com.example.electronicshop.servlets.CartServlets.COMMAND_DELETE_PRODUCT;

public class CartImpl implements CartService {
    private Map<Product, Integer> cart;
    private CartRepository cartRepository;
    private TransactionManager transactionManager;
    public static final String ID = "id";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";


    public CartImpl(CartRepository cartRepository, TransactionManager transactionManager) {
        this.cartRepository = cartRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public void addProduct(HttpServletRequest request) {
        int productId;
        int productQuantity = 1;

        try {
            productId = Integer.parseInt(request.getParameter(ADD_TO_CART));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return;
        }
        if (request.getSession(false).getAttribute(CART_INFO) != null) {
            CartInfo cartInfo = (CartInfo) request.getSession(false).getAttribute(CART_INFO);
            cart = cartInfo.getCart();
        }
        if (cart == null) {
            cart = new HashMap<>();
        }
        Product product = transactionManager.doWithoutTransaction(() -> cartRepository.getProductById(productId));
        int quantity = cart.containsKey(product) ? cart.get(product) + productQuantity : productQuantity;
        cart.put(product, quantity);
    }

    @Override
    public void deleteProduct(HttpServletRequest request) {
        int productId;
        double price;
        Product product;
        try {
            productId = Integer.parseInt(request.getParameter(COMMAND_DELETE_PRODUCT));
            price = Double.parseDouble(request.getParameter(PRICE));
            product = transactionManager.doWithoutTransaction(() -> cartRepository.getProductById(productId));
            product.setPrice(price);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return;
        }
        cart.remove(product);
    }

    @Override
    public List<Product> productsInCart() {
        List<Product> productList = new ArrayList<>();
        for (Entry<Product, Integer> entry : cart.entrySet()
        ) {
            productList.add(entry.getKey());
        }
        return productList;
    }

    @Override
    public int productQuantity(int productId) {
        for (Entry<Product, Integer> entry : cart.entrySet()
        ) {
            if (entry.getKey().getProductId() == productId) {
                return entry.getValue();
            }
        }
        return 0;
    }

    @Override
    public int totalQuantity() {
        int quantity = 0;
        for (Entry<Product, Integer> entry : cart.entrySet()
        ) {
            quantity += entry.getValue();
        }
        return quantity;
    }

    @Override
    public double cartPrice() {
        double totalPrice = 0;
        for (Entry<Product, Integer> entry : cart.entrySet()
        ) {
            totalPrice += (entry.getKey().getPrice() * entry.getValue());
        }
        return totalPrice;
    }

    @Override
    public void updateCart(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] stringsID = parameterMap.get(ID);
        String[] prices = parameterMap.get(PRICE);
        String[] stringsQuantity = parameterMap.get(QUANTITY);
        Map<Product, Integer> tempCart = cart;
        for (int i = 0; i < stringsID.length; i++) {
            try {
                int id = Integer.parseInt(stringsID[i]);
                double price = Double.parseDouble(prices[i]);
                Product product = transactionManager.doWithoutTransaction(() ->
                        cartRepository.getProductByIdAndPrice(id, price));
                if (Integer.parseInt(stringsQuantity[i]) > 0 && product != null) {
                    tempCart.put(product, Integer.parseInt(stringsQuantity[i]));
                }
                if(Integer.parseInt(stringsQuantity[i]) < 1 && product != null){
                    tempCart.remove(product);
                }
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
        cart = tempCart;
    }

    @Override
    public void clearCart(HttpServletRequest request) {
        request.getSession(false).removeAttribute(CART_INFO);
        cart = new HashMap<>();
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public CartInfo getCartInfo() {
        CartInfo cartInfo = new CartInfo();
        if (cart == null || cart.isEmpty()) {
            return cartInfo;
        }
        cartInfo.setTotalPrice(cartPrice());
        cartInfo.setTotalQuantity(totalQuantity());
        cartInfo.setCart(cart);
        return cartInfo;
    }

}
