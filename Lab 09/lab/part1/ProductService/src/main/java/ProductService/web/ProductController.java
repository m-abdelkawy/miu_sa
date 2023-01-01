package ProductService.web;

import ProductService.domain.Product;
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
    public Product getProduct(@PathVariable String productNumber){
        String url = stockServiceUrl + "/stock/" + productNumber;
        int numberInStock = restTemplate.getForObject(url, Integer.class);
        return new Product(productNumber, "Iphone 12", numberInStock);
    }

    @Bean
    public RestOperations getRestTemplate(){
        return new RestTemplate();
    }
}
