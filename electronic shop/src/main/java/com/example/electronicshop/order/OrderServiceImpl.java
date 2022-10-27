package com.example.electronicshop.order;

import com.example.electronicshop.cart.CartInfo;
import com.example.electronicshop.dao.OrderRepository;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.users.SpecificUser;
import com.example.electronicshop.validate.ValidateOrder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import static com.example.electronicshop.constants.Pages.CART_PAGE;
import static com.example.electronicshop.constants.ServletsName.PRODUCT_LIST_SERVLET;
import static com.example.electronicshop.service.impl.UploadAvatar.SPECIFIC_USER;
import static com.example.electronicshop.servlets.CartServlets.CART_INFO;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final TransactionManager transactionManager;
    private final ValidateOrder validateOrder;
    private Map<String, String> errorMap;

    public OrderServiceImpl(OrderRepository orderRepository, TransactionManager transactionManager, ValidateOrder validateOrder) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
        this.validateOrder = validateOrder;
    }

    @Override
    public DeliveryAndPayment getDeliveryPayment() {
        DeliveryAndPayment deliveryAndPayment = new DeliveryAndPayment();
        deliveryAndPayment.setPayment(transactionManager.doWithoutTransaction(() -> {
            deliveryAndPayment.setDelivery(orderRepository.getShippers());
            return orderRepository.getPayments();
        }));
        return deliveryAndPayment;
    }

    @Override
    public void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        OrderDetails orderDetails = orderVerifier(request,response);
        Order order = orderDetails.getOrder();
        SpecificUser user = (SpecificUser) session.getAttribute(SPECIFIC_USER);
        if (user != null) {
            order.setUserId(user.getUserId());
        }
        order.setStatusId(1);
        order.setDate(new Date());
        transactionManager.doInTransaction(() -> {
            for (Entry<Product, Integer> entry : orderDetails.getOrderCart().entrySet()
            ) {
                int quantity = orderRepository.getAvailableQuantity(entry.getKey().getProductId());
                if (entry.getValue() > quantity) {
                    errorMap.put("errorProduct" + entry.getKey().getProductId(), quantity + " pcs available");
                } else {
                    orderRepository.updateAvailableQuantity(entry.getKey().getProductId(), quantity - entry.getValue());
                }
            }
            if (!errorMap.isEmpty()) {
                session.setAttribute("error", errorMap);
                try {
                    response.sendRedirect(CART_PAGE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                throw new SQLException();
            }
            order.setReceiverId(orderRepository.insertRecipient(orderDetails, user.getUserId()));
            order.setOrderId(orderRepository.insertOrder(order));
            return orderRepository.insertOrderDetails(order);
        }, Connection.TRANSACTION_READ_COMMITTED);
        if (errorMap  == null || errorMap.isEmpty()) {
            session.removeAttribute("error");
            session.removeAttribute(CART_INFO);
            response.sendRedirect(PRODUCT_LIST_SERVLET);
        }
    }

    @Override
    public OrderDetails orderVerifier(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        OrderDetails orderDetails = validateOrder.readRequest(request);
        errorMap = validateOrder.validate(orderDetails);
        CartInfo cartInfo = (CartInfo) session.getAttribute(CART_INFO);
        SpecificUser user = (SpecificUser) session.getAttribute(SPECIFIC_USER);
        if (cartInfo == null || cartInfo.getCart() == null) {
            errorMap.put("cartError", "Cart is empty");
            session.setAttribute("error", errorMap);
            response.sendRedirect(CART_PAGE);
        } else {
            cartInfo.setCart(orderDetails.getOrderCart());
            orderDetails.setTotalPrice(cartInfo.getTotalPrice());
        }
        if (user == null) {
            errorMap.put("userError", "First you need to log in or register");
        }
        return orderDetails;
    }

    @Override
    public void showUserOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SpecificUser specificUser = (SpecificUser) session.getAttribute(SPECIFIC_USER);
        if(specificUser != null) {
            UserOrders userOrders = transactionManager.doInTransaction(() -> {
                UserOrders userOrder = new UserOrders();
                userOrder.setOrderInfoList(orderRepository.getUserOrders(1));
                userOrder.setOrderProduct(orderRepository.getOrderProduct(1));
                return userOrder;
            }, Connection.TRANSACTION_READ_COMMITTED);
            session.setAttribute("orderInfo", userOrders);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("orders.jsp");
        requestDispatcher.forward(request,response);
    }
}
