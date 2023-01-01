package com.example.webshop.domain.shopping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Document
public class ShoppingCart {
    @Id
    private String cartId;
    private List<CartLine> lstCartLine = new ArrayList<>();

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartLine> getLstCartLine() {
        return lstCartLine;
    }

    public void setLstCartLine(List<CartLine> lstCartLine) {
        this.lstCartLine = lstCartLine;
    }

    public void addToShoppingCart(Product product, int quantity) {
        for (CartLine cartLine : lstCartLine) {
            if (cartLine.getProduct().getProductNumber().equals(product.getProductNumber())) {
                cartLine.setQuantity(cartLine.getQuantity() + quantity);
                return;
            }
        }

        CartLine cartLine = new CartLine();
        cartLine.setProduct(product);
        cartLine.setQuantity(quantity);
        lstCartLine.add(cartLine);
    }

    public void removeFromShoppingCart(Product product) {
        Iterator<CartLine> itr = lstCartLine.iterator();
        while (itr.hasNext()) {
            CartLine cartLine = itr.next();
            if (cartLine.getProduct().getProductNumber().equals(product.getProductNumber())) {
                if (cartLine.getQuantity() > 1) {
                    cartLine.setQuantity(cartLine.getQuantity() - 1);
                } else {
                    itr.remove();
                }
            }
        }
    }

    private double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartLine cartLine : lstCartLine) {
            totalPrice += cartLine.getProduct().getPrice() * cartLine.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId='" + cartId + '\'' +
                ", lstCartLine=" + lstCartLine +
                ", totalPrice=$" + getTotalPrice() +
                '}';
    }
}
