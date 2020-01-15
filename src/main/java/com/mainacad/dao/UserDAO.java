package com.mainacad.dao;

import com.mainacad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //предоставляет набор готовых реализаций для создания DAO
public interface UserDAO extends JpaRepository<User, Integer> { //это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
    User getFirstByLoginAndPassword(String login222, String password111);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE first_name=:firstName AND last_name=:lastName")
    List<User> getAllBySomeFilters(String firstName, String lastName);

    User getFirstByLogin(String login);

}
