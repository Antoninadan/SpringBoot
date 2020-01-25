package com.mainacad.service;

import com.mainacad.dao.ItemDAO;
import com.mainacad.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemDAO itemDAO;

    public Item getById(Integer id) {
        Optional<Item> cart = itemDAO.findById(id);
        if (cart.isEmpty()) {
            return null;
        }
        return cart.get();
    }

    public List<Item> getAllByCart(Integer cartId) {
        return itemDAO.getAllByCart(cartId);
    }

    public List<Item> getAllAvailable() {
        return itemDAO.getAllAvailable();
    }

    // CRUD
    public Item save(Item cart) {
        if (cart.getId() == null) {
            return itemDAO.save(cart);
        }
        return null;
    }

    public Item update(Item cart) {
        if (cart.getId() != null && itemDAO.getOne(cart.getId()) != null) {
            return itemDAO.save(cart);
        }
        return null;
    }

    public List<Item> getAll() {
        return itemDAO.findAll();
    }

    public void delete(Item cart) {
        itemDAO.delete(cart);
    }

    public void deleteById(Integer id) throws RuntimeException {
        itemDAO.deleteById(id);
    }
}
