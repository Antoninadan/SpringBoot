package com.mainacad.controller.rest;

import com.mainacad.model.Cart;
import com.mainacad.model.Status;
import com.mainacad.service.CartService;
import com.mainacad.util.MapperCartUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@Controller
//@Scope(value = "session")
@RestController
@RequestMapping("cart")
@Slf4j
@Profile("rest")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    MapperCartUtil mapperCartUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String requestBody) {
        Cart savedCart = cartService.save(mapperCartUtil.toCart(mapperCartUtil.toCartDTO(requestBody)));
        if (savedCart == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(mapperCartUtil.toCartDTOFromCart(savedCart), HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getCart(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Cart cart = cartService.getById(id);
            if (cart == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(mapperCartUtil.toCartDTOFromCart(cart), HttpStatus.OK);
        } else {
            return new ResponseEntity(mapperCartUtil.toCartDTOListFromCartList(cartService.getAll()), HttpStatus.OK);
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
        return new ResponseEntity(mapperCartUtil.toCartDTOListFromCartList(cartService.getAllByUserAndPeriod(userId, timeFrom, timeTo)), HttpStatus.OK);
    }

    @GetMapping("by-user-open-status/{userId}")
    public ResponseEntity getByUserAndOpenStatus(@PathVariable Integer userId) {
        return new ResponseEntity(mapperCartUtil.toCartDTOListFromCartList(cartService.getByUserAndOpenStatus(userId)), HttpStatus.OK);
    }

    @PostMapping("update-status")
    public ResponseEntity updateStatus(@RequestBody String body) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(body);
        cartService.updateStatus((Integer) map.get("cartId"), Status.valueOf((String) map.get("status")));
        int updatedRows =  cartService.updateStatus((Integer) map.get("cartId"), Status.valueOf((String) map.get("status")));
        if (updatedRows < 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
