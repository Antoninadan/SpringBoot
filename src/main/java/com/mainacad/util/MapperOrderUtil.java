package com.mainacad.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainacad.dao.OrderDAO;
import com.mainacad.dao.UserDAO;
import com.mainacad.dao.dto.OrderDTO;
import com.mainacad.model.Order;
import com.mainacad.model.Status;
import com.mainacad.service.CartService;
import com.mainacad.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MapperOrderUtil {
    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Autowired
    ObjectMapper objectMapper;

    public Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setItem(itemService.getById(orderDTO.getItemId()));
        order.setCart(cartService.getById(orderDTO.getCartId()));
        order.setAmount(orderDTO.getAmount());
        return order;
    }

    public OrderDTO toOrderDTO(String request) {
        try {
            return objectMapper.readValue(request, OrderDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OrderDTO toOrderDTOFromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setItemId(order.getItem().getId());
        orderDTO.setCartId(order.getCart().getId());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setItemName(order.getItem().getName());
        orderDTO.setItemPrice(order.getItem().getPrice());
        return orderDTO;

    }

    public List<OrderDTO> toOrderDTOListFromOrderList(List<Order> orders) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order each : orders) {
            OrderDTO orderDTO = toOrderDTOFromOrder(each);
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

    public String jsonOrderDTOSimpleFormat(Order order) {
        OrderDTO orderDTO = toOrderDTOFromOrder(order);
        return "{" + System.lineSeparator() +
                "  \"" + "id" + "\" : " + orderDTO.getId() + "," + System.lineSeparator() +
                "  \"" + "itemId" + "\" : " + orderDTO.getItemId() + "," + System.lineSeparator() +
                "  \"" + "cartId" + "\" : " + orderDTO.getCartId() + "," + System.lineSeparator() +
                "  \"" + "amount" + "\" : " + orderDTO.getAmount() + System.lineSeparator() +
                "}";
    }

    public String jsonOrderDTOSimpleFormatList(List<Order> orders) {
        List<String> list = new ArrayList<>();
        for (Order each : orders) {
            String string = System.lineSeparator() + jsonOrderDTOSimpleFormat(each);
            list.add(string);
        }
        return list.toString();
    }

    public String jsonOrderDTOItemFormat(Order order) {
        OrderDTO orderDTO = toOrderDTOFromOrder(order);
        return "{" + System.lineSeparator() +
                "  \"" + "id" + "\" : " + orderDTO.getId() + "," + System.lineSeparator() +
                "  \"" + "itemId" + "\" : " + orderDTO.getItemId() + "," + System.lineSeparator() +
                "  \"" + "cartId" + "\" : " + orderDTO.getCartId() + "," + System.lineSeparator() +
                "  \"" + "amount" + "\" : " + orderDTO.getAmount() + System.lineSeparator() +
                "  \"" + "itemName" + "\" : " + orderDTO.getItemName() + System.lineSeparator() +
                "  \"" + "itemPrice" + "\" : " + orderDTO.getItemPrice() + System.lineSeparator() +
                "}";
    }

    public String jsonOrderDTOItemFormatList(List<Order> orders) {
        List<String> list = new ArrayList<>();
        for (Order each : orders) {
            String string = System.lineSeparator() + jsonOrderDTOItemFormat(each);
            list.add(string);
        }
        return list.toString();
    }
}
