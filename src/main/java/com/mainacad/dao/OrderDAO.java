package com.mainacad.dao;

import com.mainacad.dao.dto.OrderDTO;
import com.mainacad.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    @Query(nativeQuery = true, value = "SELECT *, o.id as order_id FROM orders o JOIN items i ON i.id=o.item_id WHERE o.cart_id= :cartId")
    public List<Order> getAllByCart(Integer cartId);

    @Query(nativeQuery = true, value = "SELECT *, o.id as order_id FROM orders o JOIN items i ON i.id=o.item_id WHERE o.cart_id= :cartId")
    public List<OrderDTO> getAllDTOByCard(Integer cartId);

    @Query(nativeQuery = true, value = "UPDATE orders SET amount = :amount WHERE id = :orderId")
    public Order updateAmount(Integer orderId, Integer amount);
}


