package orders;

import config.Configuration;
import java.util.Map;

public class CounterOrderGenerator extends OrderGenerator {
    @Override
    protected Order createDocument(Configuration configuration, Map<String, String> data) {
        Order order = new Order("COUNTER");
        order.addDetail("customer", data.get("customer"));
        order.addDetail("paymentMethod", data.get("paymentMethod"));
        return order;
    }


}