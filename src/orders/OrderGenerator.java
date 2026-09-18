package orders;
import config.Configuration;

import java.util.Map;

//Factory Methods Pattern
public abstract class OrderGenerator {

    public final Order generateOrder(Configuration config, Map<String, String> data) {
        Order order = createDocument(config, data); // <-- delega a la subclase crear el doc
        order.addDetail("productLine", config.getProductLine());
        return order;
    }

    protected abstract Order createDocument(Configuration configuration, Map<String, String> data);

}
