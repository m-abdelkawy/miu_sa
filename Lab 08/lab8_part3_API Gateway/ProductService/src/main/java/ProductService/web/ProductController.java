package ProductService.web;

import ProductService.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private StockFeignClient stockFeignClient;

    @RequestMapping("/product/{productNumber}")
    public Product getProduct(@PathVariable String productNumber){
        int numberInStock = stockFeignClient.getProductCount(productNumber);
        return new Product(productNumber, "Iphone 12", numberInStock);
    }

    @FeignClient("stock-service")
    interface StockFeignClient{
        @RequestMapping("/stock/{productNumber}")
        public int getProductCount(@PathVariable String productNumber);
    }
}
