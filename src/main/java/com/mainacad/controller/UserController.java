package com.mainacad.controller;

import com.mainacad.model.User;
import com.mainacad.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @GetMapping({"", "{id}"})
    public ResponseEntity getUser(@PathVariable(required = false) Integer id) {
        if (id != null) {
            User user = userService.getById(id);
            if (user == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(userService.getAll(), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody User user) {
        try {
            userService.delete(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Bad user params");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("Wrong id = %d", id));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
