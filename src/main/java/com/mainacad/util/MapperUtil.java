package com.mainacad.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainacad.dao.CartDAO;
import com.mainacad.dao.UserDAO;
import com.mainacad.dao.model.CartDTO;
import com.mainacad.model.Cart;
import com.mainacad.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {
    @Autowired
    UserDAO userDAO;

    @Autowired
    ObjectMapper objectMapper;

    public Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setStatus(Status.valueOf(cartDTO.getStatus()).); //to uppercase
        cart.setUser(userDAO.getOne(cartDTO.getUserId()));
        cart.setCreationTime(cartDTO.getCreationTime());
        return cart;
    }

    public CartDTO toCartDTO(String requestBody) {
        try {
            return objectMapper.readValue(requestBody, CartDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
