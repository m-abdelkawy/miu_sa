package com.example.ProductServiceWithEureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private StockFeignClient stockFeignClient;

    @GetMapping("/product/{productNumber}")
    public Product getProduct(@PathVariable("productNumber") String productNumber){
        int numberOnStock = stockFeignClient.getProductCount(productNumber);
        return new Product(productNumber, "Iphone 12", numberOnStock);
    }

    @FeignClient("StockService")
    public interface StockFeignClient {
        @RequestMapping("/stock/{productNumber}")
        public int getProductCount(@PathVariable("productNumber")String productNumber);
    }
}
