package com.mainacad.dao;

import com.mainacad.dao.model.CartSumDTO;
import com.mainacad.factory.ConnectionFactory;
import com.mainacad.model.Cart;
import com.mainacad.model.Item;
import com.mainacad.model.Status;
import com.mainacad.model.User;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public interface CartDAO extends JpaRepository<Cart, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE user_id = :userId AND creation_time >= :timeFrom AND creation_time <= :timeTo")
    public List<Cart> getAllByUserAndPeriod(User user, Long timeFrom, Long timeTo);

    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE user_id = :id AND status=0")
    public Cart getByUserAndOpenStatus(Integer id);

    // TODO statusOrdinal
    @Query(nativeQuery = true, value = "UPDATE carts SET statusc = :status WHERE id = :id")
    public Cart updateStatus(Integer id, int statusOrdinal);


//    public List<CartSumDTO> getItemsSumGroupedByUser(Long timeFrom, Long timeTo) {
//        Session session = connectionFactory.getSessionFactory().openSession();
//        session.getTransaction().begin();
//
//        String sql = "SELECT u.login, SUM(o.amount * i.price) as sum_items, MIN(c.creation_time) as creat_time FROM carts c " +
//                "JOIN users u ON c.user_id = u.id " +
//                "JOIN orders o ON c.id = o.cart_id " +
//                "JOIN items i ON i.id = o.item_id " +
//                "WHERE c.creation_time >= ? AND c.creation_time <= ? AND c.status = 2 " +
//                "GROUP BY u.login " +
//                "ORDER BY MIN(c.creation_time)";
//        List<CartSumDTO> result = session.createNativeQuery(sql).addEntity(Cart.class).getResultList();
//        session.close();
//
//        return result;
//    }
}