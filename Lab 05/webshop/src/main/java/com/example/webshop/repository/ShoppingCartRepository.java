package com.example.webshop.repository;

import com.example.webshop.domain.shopping.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
