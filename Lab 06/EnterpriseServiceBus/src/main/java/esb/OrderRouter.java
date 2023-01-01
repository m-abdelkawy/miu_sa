package esb;

public class OrderRouter {
    public String route(Order order){
        String destinationChannel = null;
        if (order.getOrderType() == OrderType.INTERNATIONAL) {
            destinationChannel = "shippingchannelinternational";
        }

        // Domestic orders
        else if(order.getOrderType() == OrderType.DOMESTIC){
            if(order.getAmount() >= 175){
                destinationChannel = "shippingchannelnextday";
            }
            else{
                destinationChannel = "shippingchannel";
            }
        }
        return destinationChannel;
    }
}
