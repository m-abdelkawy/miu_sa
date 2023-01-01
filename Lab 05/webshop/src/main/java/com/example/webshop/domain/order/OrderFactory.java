package com.example.webshop.domain.order;

import com.example.webshop.domain.shopping.ShoppingCart;
import com.example.webshop.domain.shopping.CartLine;

import java.util.Date;

public class OrderFactory {
    public static Order createOrder(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getCartId(), new Date(), "placed");
        for (CartLine cartLine : shoppingCart.getLstCartLine()) {
            OrderLine orderLine = new OrderLine();
            Product product = new Product(cartLine.getProduct().getProductNumber(),
                    cartLine.getProduct().getPrice(),
                    cartLine.getProduct().getDescription());
            orderLine.setProduct(product);
            orderLine.setQuantity(cartLine.getQuantity());
            order.addOrderLine(orderLine);
        }
        return order;
    }
}
