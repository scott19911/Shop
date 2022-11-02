package com.example.electronicshop.servlets;

import com.example.electronicshop.cart.CartService;
import com.example.electronicshop.order.DeliveryAndPayment;
import com.example.electronicshop.order.OrderService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.electronicshop.constants.Pages.CART_PAGE;
import static com.example.electronicshop.utils.ContextListener.CART_SERVICE;
import static com.example.electronicshop.utils.ContextListener.ORDER_SERVICE;

@WebServlet("/cart")
public class CartServlets extends HttpServlet {
    public static final String CART_INFO = "cartInfo";
    public static final String REQUEST_CAME_FROM = "cameFrom";
    public static final String DELIVERY_AND_PAYMENT = "payment";
    public static final String COMMAND = "command";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CartService cartService = (CartService) req.getServletContext().getAttribute(CART_SERVICE);
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(ORDER_SERVICE);
        DeliveryAndPayment deliveryAndPayment = orderService.getDeliveryPayment();
        String cameFrom = req.getParameter(REQUEST_CAME_FROM);
        HttpSession session = req.getSession();
        if(req.getParameter(COMMAND) != null){
            cartService.updateCart(req);
            session.setAttribute(CART_INFO, cartService.getCartInfo());
        }
        session.setAttribute(DELIVERY_AND_PAYMENT, deliveryAndPayment);
        if (cameFrom != null) {
            resp.sendRedirect(cameFrom);
        } else {
            resp.sendRedirect(CART_PAGE);
        }
    }


}
