package com.example.electronicshop.validate;

import com.example.electronicshop.cart.CartInfo;
import com.example.electronicshop.order.DeliveryNP;
import com.example.electronicshop.order.OrderDetailsDTO;
import com.example.electronicshop.products.Product;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.example.electronicshop.servlets.CartServlets.CART_INFO;

public class ValidateOrderImpl implements ValidateOrder {
    private final static String SHOP_CITY = "Kharkov";
    private final static String SHOP_STREET = "Sumska";
    private final static String SHOP_HOUSE = "16/17";
    private final static int DELIVERY_NP = 1;
    private final static int DELIVERY_UKRPOSHTA = 2;

    @Override
    public OrderDetailsDTO readRequest(HttpServletRequest request) {
        int delivery = 0;
        OrderDetailsDTO orderDetailsDTO;
        if (request.getParameter("delivery") != null) {
            try {
                delivery = Integer.parseInt(request.getParameter("delivery"));
            } catch (NumberFormatException ex) {
              ex.printStackTrace();
            }

        }
        switch (delivery) {
            case DELIVERY_NP:
                orderDetailsDTO = deliveryNP(request);
                break;
            case DELIVERY_UKRPOSHTA:
                orderDetailsDTO = deliveryUkrPoshta(request);
                break;
            default:
                orderDetailsDTO = deliveryPickUp(request);
        }
        orderDetailsDTO.setDelivery(delivery);
        orderDetailsDTO.setDepartment(request.getParameter("department"));
        orderDetailsDTO.setOrderCart(cartCopy(request));
        try {
            orderDetailsDTO.setPayment( Integer.parseInt(request.getParameter("payment")));
        }catch (NumberFormatException ex){
            orderDetailsDTO.setPayment(1);
        }
        if (request.getParameter("payment") != null &&
                !request.getParameter("payment").isBlank()
                && Integer.parseInt(request.getParameter("payment")) == 2) {
            orderDetailsDTO.setCardNumber(request.getParameter("card"));
            orderDetailsDTO.setDataCard(request.getParameter("data"));
            orderDetailsDTO.setCvv2(request.getParameter("cvv2"));
        }
        return orderDetailsDTO;
    }

    @Override
    public Map<String, String> validate(OrderDetailsDTO orderDetails) {
        Map<String, String> errorMap = new HashMap<>();
        String regexName = "^[\\w'а-яА-Я\\-,.][^0-9_!¡?÷?¿/\\\\+=@#$%ˆ&*(){}|~<>;:]{2,}";
        String regexCity = "^[a-zA-Zа-яА-Я]+(?:[\\s-][a-zA-Zа-яА-Я]+)*$";
        String regexPhone = "((\\+38)?\\(?\\d{3}\\)?[\\s\\.-]?(\\d{7}|\\d{3}[\\s\\.-]\\d{2}[\\s\\.-]\\d{2}|\\d{3}-\\d{4}))";
        String regexCard = "\\d+ ?\\d+[^a-zA-zА-Яа-я]+";
        String regexCardData = "\\d{2} ?\\/?\\d{2}";
        String regexCardCvv = "\\d{3}";
        errorMap("valName", "Incorrect First Name", orderDetails.getRecipientName(), regexName, errorMap);
        errorMap("valSurname", "Incorrect Surname", orderDetails.getRecipientSurname(), regexName, errorMap);
        errorMap("valCity", "Incorrect City", orderDetails.getCity(), regexCity, errorMap);
        errorMap("valPhone", "Incorrect Phone number", orderDetails.getRecipientPhone(), regexPhone, errorMap);
        if (orderDetails.getCardNumber() != null) {
            errorMap("valCard", "Incorrect Card number", orderDetails.getCardNumber(), regexCard, errorMap);
            errorMap("valCardData", "Incorrect Card data", orderDetails.getDataCard(), regexCardData, errorMap);
            errorMap("valCardCVV2", "Incorrect CVV2", orderDetails.getCvv2(), regexCardCvv, errorMap);
        }

        return errorMap;
    }

    private OrderDetailsDTO deliveryNP(HttpServletRequest request) {
        int department = 0;
        if (request.getParameter("department") != null) {
            try {
                department = Integer.parseInt(request.getParameter("department"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        DeliveryNP deliveryNP = new DeliveryNP(department);
        return OrderDetailsDTO.newBuilder().setCity(request.getParameter("city")).setHouse(deliveryNP.getHouse())
                .setPhone(request.getParameter("phone")).setStreet(deliveryNP.getStreet())
                .setRecipientName(request.getParameter("firstName")).setRecipientSurname(request.getParameter("surname"))
                .build();
    }

    private OrderDetailsDTO deliveryUkrPoshta(HttpServletRequest request) {
        return OrderDetailsDTO.newBuilder().setCity(request.getParameter("city")).setHouse(request.getParameter("apartment"))
                .setPhone(request.getParameter("phone")).setStreet(request.getParameter("street"))
                .setRecipientName(request.getParameter("firstName")).setRecipientSurname(request.getParameter("surname"))
                .build();

    }

    private OrderDetailsDTO deliveryPickUp(HttpServletRequest request) {
        return OrderDetailsDTO.newBuilder().setCity(SHOP_CITY).setHouse(SHOP_HOUSE)
                .setPhone(request.getParameter("phone")).setStreet(SHOP_STREET)
                .setRecipientName(request.getParameter("firstName")).setRecipientSurname(request.getParameter("surname"))
                .build();

    }

    private Map<Product, Integer> cartCopy(HttpServletRequest request) {
        Map<Product, Integer> cart;
        if (request.getSession(false).getAttribute(CART_INFO) != null) {
            CartInfo cartInfo = (CartInfo) request.getSession(false).getAttribute(CART_INFO);
            cart = cartInfo.getCart();
            Map<Product, Integer> copy = new HashMap<>();
            if(cart != null && !cart.isEmpty()) {
                for (Entry<Product, Integer> product : cart.entrySet()
                ) {
                    copy.put((Product) product.getKey().clone(), product.getValue());
                }
            }
            return copy;
        }
        return null;
    }

    private boolean checkRegex(String parameter, String regex) {
        return parameter.isEmpty() || !parameter.matches(regex);
    }

    private void errorMap(String key, String errorMassage, String parameter, String regex, Map<String, String> errorMap) {
        if (parameter == null || parameter.isBlank() || checkRegex(parameter, regex)) {
            errorMap.put(key, errorMassage);
        }
    }
}
