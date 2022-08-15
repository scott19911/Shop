package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.service.CartService;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;
import com.epam.verizhenko_andrii.electronicsStore.service.OrderHistoryService;
import com.epam.verizhenko_andrii.electronicsStore.service.ProductsService;


import java.util.Scanner;

/**
 * The class is responsible for adding a product to the cart based on the entered product brand and quantity.
 */
public class AddToBucket implements Commands {
    @Override
    public Events execute(Events event) {
        OrderHistoryService historyService = new OrderHistoryService(event.getHistory());
        Scanner sc = new Scanner(System.in);
        System.out.println("enter product brand: ");
        String brand = sc.nextLine();
        System.out.println("enter quantity of products: ");
        int bayQuantity = sc.nextInt();
        CartService cart = new CartService(event.getCart());
        int avalaibleQuantity;
        ProductsService map = new ProductsService(event.getProductsMap());
        Product product = map.getProduct(brand);
        System.out.println(brand);
        if (product.getBrand() == null) {
            throw new IllegalArgumentException("incorrect brand of goods");
        }
        avalaibleQuantity = map.getQuantity(product);
        if (bayQuantity > avalaibleQuantity || bayQuantity <= 0) {
            throw new IllegalArgumentException("incorrect quantity of goods");
        }
        avalaibleQuantity -= bayQuantity;
        map.updateProducts(product, avalaibleQuantity);
        historyService.addToHistory(product);
        event.setHistory(historyService.getOrderHistory());
        event.setProductsMap(map.getProductsDao());
        if (cart.alreadyAddBrand(brand) > 0) {
            bayQuantity += cart.alreadyAddBrand(brand);
        }
        cart.addToCart(product.getBrand(), bayQuantity);
        event.setCart(cart.getCartDao());
        return event;
    }
}
