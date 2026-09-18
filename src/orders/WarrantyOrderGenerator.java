package orders;

import config.Configuration;
import java.util.Map;

public class WarrantyOrderGenerator extends OrderGenerator {

    @Override
    protected Order createDocument(Configuration configuration, Map<String, String> data) {
        Order order = new Order("WARRANTY");
        order.addDetail("originalOrderNumber", data.get("originalOrderNumber"));
        order.addDetail("faultDescription", data.get("faultDescription"));
        return order;
    }
}