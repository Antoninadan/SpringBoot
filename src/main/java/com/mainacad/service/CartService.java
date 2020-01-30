package com.mainacad.service;

import com.mainacad.dao.CartDAO;
import com.mainacad.model.Cart;
import com.mainacad.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartDAO cartDAO;

    public Cart getById(Integer id) {
        Optional<Cart> cart = cartDAO.findById(id);
        if (cart.isEmpty()) {
            return null;
        }
        return cart.get();
    }

    public List<Cart> getAllByUserAndPeriod(Integer userId, Long timeFrom, Long timeTo) {
        return cartDAO.getAllByUserAndPeriod(userId, timeFrom, timeTo);
    }

    public List<Cart> getByUserAndOpenStatus(Integer userId) {
        return cartDAO.getByUserAndOpenStatus(userId);
    }

    public int updateStatus(Integer cartId, Status status) {
        return cartDAO.updateStatus(cartId, status.ordinal());
    }

    // CRUD
    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            return cartDAO.save(cart);
        }
        return null;
    }

    public List<Cart> getAll() {
        return cartDAO.findAll();
    }

    public void delete(Cart cart) {
        cartDAO.delete(cart);
    }

    public void deleteById(Integer id) throws RuntimeException {
        cartDAO.deleteById(id);
    }
}
