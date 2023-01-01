package com.example.webshop.controller;

import com.example.webshop.domain.order.Order;
import com.example.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderNumber}")
    public ResponseEntity<?> getShoppingCart(@PathVariable String orderNumber){
        Order order = orderService.getOrder(orderNumber);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
