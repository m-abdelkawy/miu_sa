package com.example.webshop.controller;

import com.example.webshop.domain.shopping.ShoppingCart;
import com.example.webshop.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class ShoppingController {
    @Autowired
    ShoppingService shoppingService;

    @PostMapping(value = "/{cartId}/{productNumber}/{quantity}")
    public ResponseEntity<?> addToShoppingCart(@PathVariable String cartId, @PathVariable String productNumber, @PathVariable int quantity) {
        shoppingService.addToShoppingCart(cartId, productNumber, quantity);
        return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getShoppingCart(@PathVariable String cartId) {
        ShoppingCart cart = shoppingService.getShoppingCart(cartId);
        return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/checkout/{cartId}")
    public ResponseEntity<?> checkout(@PathVariable String cartId) {
        shoppingService.checkout(cartId);
        return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
    }
}
