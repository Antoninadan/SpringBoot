package com.mainacad.dao;

import com.mainacad.AppRunner;
import com.mainacad.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppRunner.class)
class UserDAOTest {
    @Autowired
    UserDAO userDAO;

    @Test
    void getAllBySomeFilters() {
        User user = new User("test_login", "test_password",
                "test_first_name", "test_last_name");
        User userSaved = userDAO.save(user);
        List<User> usersFound = userDAO.getAllBySomeFilters("test_first_name", "test_last_name");

        assertNotNull(usersFound);
        assertTrue(!usersFound.isEmpty());
        assertTrue(usersFound.size() == 1);
        assertEquals(usersFound.get(0).getFirstName(), userSaved.getFirstName());

        // example
        try {
            userDAO.deleteById(Integer.MAX_VALUE);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }
}