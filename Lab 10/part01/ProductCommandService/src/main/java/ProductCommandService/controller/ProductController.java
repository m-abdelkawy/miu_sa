package ProductCommandService.controller;

import ProductCommandService.model.Product;
import ProductCommandService.model.ProductQuery;
import ProductCommandService.model.StockDto;
import ProductCommandService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    private RestTemplate restTemplate;

    public ProductController() {
        this.restTemplate = new RestTemplate();
    }

    private String stockServiceUrl = "http://localhost:8082/stock";
    private String productQueryServiceUrl = "http://localhost:8083/products";

    @GetMapping("/hello")
    public String hello(){
        System.out.println("hello from ProductController");
        return "hello from ProductController";
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        Product productAdded = productService.addProduct(product);

        StockDto stockDto = restTemplate
                .postForObject(stockServiceUrl + "/add", new StockDto(productAdded.getProductNumber(), 0), StockDto.class);

        ProductQuery productQuery = restTemplate
                .postForObject(productQueryServiceUrl + "/add",
                        new ProductQuery(productAdded.getProductNumber(), productAdded.getName(), productAdded.getPrice(), 0), ProductQuery.class);

        return productAdded;
    }

    @PostMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PostMapping("/delete/{productNumber}")
    public Product deleteProduct(@PathVariable int productNumber) {
        StockDto stockDto = restTemplate.postForObject(stockServiceUrl + "/delete/" + productNumber, null, StockDto.class);
        return productService.deleteProduct(productNumber);
    }

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }
}
