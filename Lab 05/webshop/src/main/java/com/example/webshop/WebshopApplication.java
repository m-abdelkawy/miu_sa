package com.example.webshop;

import com.example.webshop.domain.order.Order;
import com.example.webshop.domain.products.Product;
import com.example.webshop.domain.shopping.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WebshopApplication implements CommandLineRunner {

    @Autowired
    private RestOperations restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WebshopApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        //create products
        restTemplate.postForLocation("http://localhost:8080/product/A1/IPhone12/350.00", null);
        restTemplate.postForLocation("http://localhost:8080/product/A2/IPad/120.00", null);
        //set stock
        restTemplate.postForLocation("http://localhost:8080/product/stock/A1/100/K12", null);
        //get a product
        Product product = restTemplate.getForObject("http://localhost:8080/product/A1", Product.class);
        System.out.println("-----Product-------");
        System.out.println(product.toString());
        // add products to the shoppingcart
        restTemplate.postForLocation("http://localhost:8080/cart/1/A1/3", null);
        restTemplate.postForLocation("http://localhost:8080/cart/1/A2/2", null);
        //get the shoppingcart
        ShoppingCart cart = restTemplate.getForObject("http://localhost:8080/cart/1", ShoppingCart.class);
        System.out.println("-----Shoppingcart-------");
        if (cart != null)
            System.out.println(cart.toString());
        //checkout the cart
        restTemplate.postForLocation("http://localhost:8080/cart/checkout/1", null);
        //get the order
        Order order = restTemplate.getForObject("http://localhost:8080/order/1", Order.class);
        System.out.println("-----Order-------");
        if (order != null)
            System.out.println(order.toString());
    }
}
