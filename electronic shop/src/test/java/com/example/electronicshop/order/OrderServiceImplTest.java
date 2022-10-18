package com.example.electronicshop.order;

import com.example.electronicshop.dao.OrderRepositoryImpl;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.validate.ValidateOrderImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.electronicshop.servlets.CartServlets.CART_INFO;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {
    public HttpServletRequest request = mock(HttpServletRequest.class);
    public HttpServletResponse response = mock(HttpServletResponse.class);
    @Test
    public void shouldReturnErrorCartAndErrorUser_whenCartIsEmptyAndUserNotLogin() throws IOException {
        HttpSession session = mock(HttpSession.class);
        HttpSession session1 = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session1);
        when(session.getAttribute("specificUser")).thenReturn(null);
        when(session1.getAttribute(CART_INFO)).thenReturn(null);
        when(session.getAttribute(CART_INFO)).thenReturn(null);
        when(request.getParameter("city")).thenReturn("Kharkov");
        when(request.getParameter("phone")).thenReturn("0988398205");
        when(request.getParameter("department")).thenReturn("2");
        when(request.getParameter("firstName")).thenReturn("Andrii");
        when(request.getParameter("surname")).thenReturn("Verizhenko");
        when(request.getParameter("delivery")).thenReturn("1");
        when(request.getParameter("payment")).thenReturn("1");
        OrderDetailsDTO orderDetailsDTO = OrderDetailsDTO.newBuilder().setCity("Kharkov").setRecipientName("Andrii")
                .setPhone("0988398205").setRecipientSurname("Verizhenko").setStreet("Prospekt Haharina").setHouse("38a").build();
        orderDetailsDTO.setDepartment("2");
        orderDetailsDTO.setPayment(1);
        orderDetailsDTO.setDelivery(1);
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("userError","First you need to log in or register");
        errorMap.put("cartError","Cart is empty");
        OrderServiceImpl orderService = new OrderServiceImpl(new OrderRepositoryImpl()
                ,new TransactionManager(), new ValidateOrderImpl());
        orderService.orderVerifier(request,response);
        verify(session,atLeastOnce()).setAttribute("order", orderDetailsDTO);
        verify(session,atLeastOnce()).setAttribute("error", errorMap);
    }

}