package com.mainacad.dao;

import com.mainacad.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface CartDAO extends JpaRepository<Cart, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE user_id = :userId AND creation_time >= :timeFrom AND creation_time <= :timeTo")
    List<Cart> getAllByUserAndPeriod(@Param("userId") Integer userId, @Param("timeFrom") Long timeFrom, @Param("timeTo") Long timeTo);

    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE user_id = :userId AND status=0")
    List<Cart> getByUserAndOpenStatus(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE carts SET status = :statusOrdinal WHERE id = :cartId")
    int updateStatus(@Param("cartId") Integer cartId, @Param("statusOrdinal") int statusOrdinal);
}