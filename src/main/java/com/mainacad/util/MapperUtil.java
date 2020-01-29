package com.mainacad.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainacad.dao.UserDAO;
import com.mainacad.dao.dto.CartDTO;
import com.mainacad.model.Cart;
import com.mainacad.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperUtil {
    @Autowired
    UserDAO userDAO;

    @Autowired
    ObjectMapper objectMapper;

    public Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setStatus(Status.valueOf(cartDTO.getStatus().toUpperCase()));
        cart.setUser(userDAO.getOne(cartDTO.getUserId()));
        cart.setCreationTime(cartDTO.getCreationTime());
        return cart;
    }

    public CartDTO toCartDTO(String request) {
        try {
            return objectMapper.readValue(request, CartDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CartDTO toCartDTOFromCart(Cart cart) {
        Integer id = cart.getId();
        Integer userId = cart.getUser().getId();
        Long creationTime = cart.getCreationTime();
        String status = cart.getStatus().name();
        return new CartDTO(id, userId, creationTime, status);
    }

    public List<CartDTO> toCartDTOListFromCartList(List<Cart> carts) {
        List<CartDTO> cartDTOs = new ArrayList<>();
        for(Cart each:carts){

            Integer id = each.getId();
            Integer userId = each.getUser().getId();
            Long creationTime = each.getCreationTime();
            String status = each.getStatus().name();
            CartDTO cartDTO = new CartDTO(id, userId, creationTime, status);
            cartDTOs.add(cartDTO);
        }
        return cartDTOs;
    }
}
