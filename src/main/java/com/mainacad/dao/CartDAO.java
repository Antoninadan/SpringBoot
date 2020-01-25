package com.mainacad.dao;

import com.mainacad.dao.model.CartSumDTO;
import com.mainacad.model.Cart;
import com.mainacad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CartDAO extends JpaRepository<Cart, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE user_id = :userId AND creation_time >= :timeFrom AND creation_time <= :timeTo")
    public List<Cart> getAllByUserAndPeriod(User user, Long timeFrom, Long timeTo);

    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE user_id = :id AND status=0")
    public Cart getByUserAndOpenStatus(Integer id);

    @Query(nativeQuery = true, value = "UPDATE carts SET status = :statusOrdinal WHERE id = :cartId")
    public Cart updateStatus(Integer cartId, int statusOrdinal);

    @Query(nativeQuery = true, value = "SELECT u.login, SUM(o.amount * i.price) as sum_items, MIN(c.creation_time) as creat_time FROM carts c " +
                                        "JOIN users u ON c.user_id = u.id " +
                                        "JOIN orders o ON c.id = o.cart_id " +
                                        "JOIN items i ON i.id = o.item_id " +
                                        "WHERE c.creation_time >= :timeFrom AND c.creation_time <= :timeTo AND c.status = 2 " +
                                        "GROUP BY u.login " +
                                        "ORDER BY MIN(c.creation_time)")
    public List<CartSumDTO> getItemsSumGroupedByUser(Long timeFrom, Long timeTo);
}