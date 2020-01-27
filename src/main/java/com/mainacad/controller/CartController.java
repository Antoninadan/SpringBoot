package com.mainacad.controller;

import com.mainacad.model.Cart;
import com.mainacad.model.Status;
import com.mainacad.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@Controller
//@Scope(value = "session")
@RestController
@RequestMapping("cart")
@Slf4j
public class CartController {
    @Autowired
    CartService cartService;

    @PutMapping
    public ResponseEntity save(@RequestBody Cart cart) {
        Cart savedCart = cartService.save(cart);
        if (savedCart == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody Cart cart) {
        Cart updatedCart = cartService.update(cart);
        if (updatedCart == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cart, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getCart(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Cart cart = cartService.getById(id);
            if (cart == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity(cartService.getAll(), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Cart cart) {
        try {
            cartService.delete(cart);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Bad cart params");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            cartService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("Wrong id = %d", id));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("by-user-period/{userId}/{timeFrom}/{timeTo}")
    public ResponseEntity getAllByUserAndPeriod(@PathVariable Integer userId, @PathVariable Long timeFrom, @PathVariable Long timeTo) {
        return new ResponseEntity(cartService.getAllByUserAndPeriod(userId, timeFrom, timeTo), HttpStatus.OK);
    }

    @GetMapping("by-user-open-status/{userId}")
    public ResponseEntity getByUserAndOpenStatus(@PathVariable Integer userId) {
        return new ResponseEntity(cartService.getByUserAndOpenStatus(userId), HttpStatus.OK);
    }

    @PostMapping("update-status")
    public ResponseEntity updateStatus(@RequestBody String body) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(body);
        Cart updatedCart = cartService.updateStatus((Integer) map.get("cartId"), Status.valueOf((String) map.get("status")) );
        if (updatedCart == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(updatedCart, HttpStatus.OK);
    }

    @GetMapping("items-sum/{timeFrom}/{timeTo}")
    public ResponseEntity getItemsSumGroupedByUser(@PathVariable Long timeFrom, @PathVariable Long timeTo) {
        return new ResponseEntity(cartService.getItemsSumGroupedByUser(timeFrom, timeTo), HttpStatus.OK);
    }


}
