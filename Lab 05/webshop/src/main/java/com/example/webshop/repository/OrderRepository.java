package com.example.webshop.repository;

import com.example.webshop.domain.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
