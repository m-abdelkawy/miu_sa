
package esb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class WarehouseActivator {

    @Autowired
    RestTemplate restTemplate;

    public Order checkStock(Order order) {
        System.out.println("WarehouseService: checking order " + order.toString());
        // International orders
        if (order.getOrderType() == OrderType.INTERNATIONAL) {
            restTemplate.postForLocation("http://localhost:8084/orders", order);
        }

        // Domestic orders
        else if(order.getOrderType() == OrderType.DOMESTIC){
            if(order.getAmount() >= 175){
                restTemplate.postForLocation("http://localhost:8083/orders", order);
            }
            else{
                restTemplate.postForLocation("http://localhost:8085/orders", order);
            }
        }
        return order;
    }
}
