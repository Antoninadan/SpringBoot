package com.mainacad.controller;

import com.mainacad.model.Order;
import com.mainacad.service.OrderService;
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
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PutMapping
    public ResponseEntity save(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);
        if (savedOrder == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody Order order) {
        Order updatedOrder = orderService.update(order);
        if (updatedOrder == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(order, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getOrder(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Order order = orderService.getById(id);
            if (order == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(order, HttpStatus.OK);
        } else {
            return new ResponseEntity(orderService.getAll(), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Order order) {
        try {
            orderService.delete(order);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Bad order params");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            orderService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("Wrong id = %d", id));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("by-cart/{orderId}")
    public ResponseEntity getAllByCart(Integer orderId) {
        return new ResponseEntity(orderService.getAllByCart(orderId), HttpStatus.OK);
    }

    @GetMapping("dto-by-cart/{orderId}")
    public ResponseEntity getAllDTOByCard(Integer orderId) {
        return new ResponseEntity(orderService.getAllDTOByCard(orderId), HttpStatus.OK);
    }

    @PostMapping("update-amount")
    public ResponseEntity updateAmount(@RequestBody String body) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(body);
        Order order = orderService.updateAmount((Integer) map.get("orderId"), (Integer) map.get("amount"));
        if (order == null) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(order, HttpStatus.OK);
    }
}
