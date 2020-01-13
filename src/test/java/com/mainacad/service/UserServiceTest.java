package com.mainacad.service;

import com.mainacad.AppRunner;
import com.mainacad.dao.UserDAO;
import com.mainacad.model.User;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppRunner.class)
class UserServiceTest {
//    @Autowired
    @MockBean
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Test
    void getByLoginAndPassword() {
        User user = new User("test_login", "test_password",
                "test_first_name", "test_last_name");
//        userDAO.save(user);
//        when(userDAO.getFirstByLoginAndPassword("test_login", "test_password"));
        when(userDAO.getFirstByLoginAndPassword(anyString(), anyString())).thenReturn(user);
        // work once!!!!!!!!!!!!!!!!!!!

        User userSaved = userService.getByLoginAndPassword("test_login", "test_password");

        assertEquals(userSaved.getFirstName(),"test_first_name");
        verify(userDAO, times(1)).getFirstByLoginAndPassword(anyString(),anyString());
    }

    @Test
    void delete() {
        User user = new User("test_login", "test_password",
                "test_first_name", "test_last_name");

        doNothing().when(userDAO).delete(any(User.class));
        userService.delete(user);

        verify(userDAO, times(1)).delete(any(User.class));

//        // example for class
//        UserDAO userDAO1 = Mockito.mock(UserDAO.class);
    }
}