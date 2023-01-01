package com.example.webshop.repository;

import com.example.webshop.domain.products.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
