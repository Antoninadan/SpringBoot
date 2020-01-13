package com.mainacad.controller;

import com.mainacad.model.User;
import com.mainacad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@Scope(value = "session")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping
    public ResponseEntity save(@RequestBody User user) {
        User savedUser = userService.save(user);
        if (savedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        if (updatedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("auth")
    public ResponseEntity getByLoginAndPassword(@RequestParam String login, @RequestParam String password) {
        User user = userService.getByLoginAndPassword(login, password);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("all")
    public ResponseEntity getAll() {
        List<User> users = userService.getAll();
        if (users == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
