package com.example.webshop.controller;

import com.example.webshop.domain.products.Product;
import com.example.webshop.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductCatalogService productCatalogService;

    @GetMapping("/{productNumber}")
    public ResponseEntity<?> getProduct(@PathVariable String productNumber){
        Product product = productCatalogService.getProduct(productNumber);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/{productNumber}/{description}/{price}")
    public ResponseEntity<?> addProduct(@PathVariable String productNumber, @PathVariable String description, @PathVariable double price){
        productCatalogService.addProduct(productNumber, description, price);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @PostMapping(value = "/stock/{productNumber}/{quantity}/{locationCode}")
    public ResponseEntity<?> setStock(@PathVariable String productNumber, @PathVariable int quantity, @PathVariable String locationCode){
        productCatalogService.setStock(productNumber, quantity, locationCode);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }
}
