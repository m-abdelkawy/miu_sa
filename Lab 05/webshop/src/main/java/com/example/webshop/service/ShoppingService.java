package com.example.webshop.service;

import com.example.webshop.domain.products.Product;
import com.example.webshop.domain.shopping.ShoppingCart;
import com.example.webshop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingService {
    @Autowired
    private ProductCatalogService productCatalogService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private OrderService orderService;

    public void addToShoppingCart(String cartId, String productNumber, int quantity){
        Product productFromPRoductCatalog = productCatalogService.getProduct(productNumber);
        if(productFromPRoductCatalog == null)
            return;
        com.example.webshop.domain.shopping.Product product = new com.example.webshop.domain.shopping.Product(productFromPRoductCatalog.getProductNumber(),
                productFromPRoductCatalog.getDescription(),
                productFromPRoductCatalog.getPrice());
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(cartId);
        if(optionalShoppingCart.isPresent()){
            ShoppingCart shoppingCart = optionalShoppingCart.get();
            shoppingCart.addToShoppingCart(product, quantity);
            shoppingCartRepository.save(shoppingCart);
        }
        else{
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setCartId(cartId);
            shoppingCart.addToShoppingCart(product, quantity);
            shoppingCartRepository.save(shoppingCart);
        }
    }

    public ShoppingCart getShoppingCart(String cartId){
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(cartId);
        if(optionalShoppingCart.isPresent()){
            return optionalShoppingCart.get();
        }
        return null;
    }

    public void checkout(String cartId){
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(cartId);
        if(optionalShoppingCart.isPresent()){
            orderService.createOrder(optionalShoppingCart.get());
        }
    }
}
