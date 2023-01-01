package com.example.webshop.service;

import com.example.webshop.domain.shopping.ShoppingCart;
import com.example.webshop.domain.order.Order;
import com.example.webshop.domain.order.OrderFactory;
import com.example.webshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order getOrder(String orderNumber){
        Optional<Order> optionalOrder = orderRepository.findById(orderNumber);
        if(optionalOrder.isPresent())
            return optionalOrder.get();
        return null;
    }

    public void createOrder(ShoppingCart shoppingCart){
        Order order = OrderFactory.createOrder(shoppingCart);
        orderRepository.save(order);
    }
}
