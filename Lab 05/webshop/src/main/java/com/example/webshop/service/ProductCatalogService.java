package com.example.webshop.service;

import com.example.webshop.domain.products.Product;
import com.example.webshop.domain.products.Stock;
import com.example.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCatalogService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(String productNumber, String description, double price){
        Product product = new Product(productNumber, description, price);
        productRepository.save(product);
    }

    public Product getProduct(String productNumber){
        Optional<Product> optionalProduct = productRepository.findById(productNumber);
        if(optionalProduct.isPresent())
            return optionalProduct.get();
        return null;
    }

    public void setStock(String productNumber, int quantity, String locationCode){
        Optional<Product> optionalProduct = productRepository.findById(productNumber);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            Stock stock = new Stock(quantity, locationCode);
            product.setStock(stock);
            productRepository.save(product);
        }
    }
}
