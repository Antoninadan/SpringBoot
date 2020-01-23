package com.mainacad.dao;

import com.mainacad.dao.model.OrderDTO;
import com.mainacad.factory.ConnectionFactory;
import com.mainacad.model.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends BaseDAO<Order> {
    @Autowired
    ConnectionFactory connectionFactory;

    public List<Order> getAllByCart(Cart cart) {
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        String sql = "SELECT *, o.id as order_id FROM orders o JOIN items i ON i.id=o.item_id " +
                "WHERE o.cart_id=?";

        NativeQuery nativeQuery = session.createNativeQuery(sql).addEntity(Order.class);
        nativeQuery.setParameter(1, cart.getId());

        List<Order> result = nativeQuery.getResultList();
        session.close();

        return result;
    }

    public List<OrderDTO> getAllDTOByCard(Cart cart) {
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

    	String sql = "SELECT o.id as order_id, " +
                "o.item_id as item_id," +
                "i.name as name," +
                "i.price as price," +
                "o.amount as amount"
    			+ "FROM orders o "
    			+ "JOIN items i ON i.id=o.item_id " +
                "WHERE o.cart_id=?";

        NativeQuery nativeQuery = session.createNativeQuery(sql).addEntity(Order.class);
        nativeQuery.setParameter(1, cart.getId());

        List<OrderDTO> result =nativeQuery.getResultList();
        session.close();

        return result;
    }
    	
    public Order updateAmount(Order order, Integer amount) {
        Session session = connectionFactory.getSessionFactory().openSession();
        session.getTransaction().begin();

        String sql = "UPDATE orders SET " +
                "amount = ? "+
                "WHERE id = ?";

        NativeQuery nativeQuery = session.createNativeQuery(sql).addEntity(Order.class);
        nativeQuery.setParameter(1, amount);
        nativeQuery.setParameter(2, order.getId());

        nativeQuery.executeUpdate();

        Order result = order;
        result.setAmount(amount);

        session.close();
        return result;
    }
}


