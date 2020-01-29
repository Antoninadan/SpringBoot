package com.mainacad.service;

import com.mainacad.dao.OrderDAO;
import com.mainacad.dao.dto.OrderDTO;
import com.mainacad.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    public Order getById(Integer id) {
        Optional<Order> order = orderDAO.findById(id);
        if (order.isEmpty()) {
            return null;
        }
        return order.get();
    }

    public List<Order> getAllByCart(Integer orderId) {
        return orderDAO.getAllByCart(orderId);
    }

    public List<OrderDTO> getAllDTOByCard(Integer orderId) {
        return orderDAO.getAllDTOByCard(orderId);
    }

    public Order updateAmount(Integer orderId, Integer amount) {
        return orderDAO.updateAmount(orderId, amount);
    }

    // CRUD
    public Order save(Order order) {
        if (order.getId() == null) {
            return orderDAO.save(order);
        }
        return null;
    }

    public Order update(Order order) {
        if (order.getId() != null && orderDAO.getOne(order.getId()) != null) {
            return orderDAO.save(order);
        }
        return null;
    }

    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    public void delete(Order order) {
        orderDAO.delete(order);
    }

    public void deleteById(Integer id) throws RuntimeException {
        orderDAO.deleteById(id);
    }
}
