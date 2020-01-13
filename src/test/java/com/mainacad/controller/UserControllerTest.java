package com.mainacad.controller;

import com.mainacad.model.User;
import com.mainacad.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void save() throws URISyntaxException {
        User user = new User();
        RequestEntity<User> request = new RequestEntity<>(user, HttpMethod.PUT, new URI("/user"));

        when(userService.save(any(User.class))).thenReturn(user);
        ResponseEntity<User> response = testRestTemplate.exchange(request, User.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void update() {
    }

    @Test
    void getByLoginAndPassword() {
    }

    @Test
    void getUser() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}