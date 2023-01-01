package service1Application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceOneController {
    @Autowired
    @Lazy
    private RestOperations restTemplate;

    private String service2url = "http://localhost:9091";

    @RequestMapping("/text")
    public String getText(){
        String path = service2url + "/text";
        String service2Text = restTemplate.getForObject(service2url + "/text", String.class);
        return "Hello " + service2Text;
    }

    @Bean
    RestOperations getRestTemplate(){
        return new RestTemplate();
    }
}
