package ProductService.web;

import ProductService.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    @Autowired
    @Lazy
    private RestOperations restTemplate;

    private String stockServiceUrl = "http://localhost:8091";

    @RequestMapping("/product/{productNumber}")
    @HystrixCommand(fallbackMethod = "getProductFallback")
    public Product getProduct(@PathVariable String productNumber){
        String url = stockServiceUrl + "/stock/" + productNumber;
        int numberInStock = restTemplate.getForObject(url, Integer.class);
        return new Product(productNumber, "Iphone 12", numberInStock);
    }

    public Product getProductFallback(String productNumber){
        System.out.println("fallback method called!");
        return new Product(productNumber, "Iphone 12", -1);
    }

    @Bean
    public RestOperations getRestTemplate(){
        return new RestTemplate();
    }
}
