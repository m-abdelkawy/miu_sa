package StockService.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @GetMapping("/stock/{productNumber}")
    public int getProductCount(@PathVariable String productNumber){
        System.out.println("getProductCount called!");
        return 105;
    }
}
