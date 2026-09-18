package orders;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {

    private final String channel;
    private final Map<String, String> details = new LinkedHashMap<>();

    public Order(String channel) {
        this.channel = channel;
    }

    public String getChanel(){
        return this.channel;
    }

    public Map<String, String> getDetails() {
        return this.details;
    }

    public void addDetail(String key, String value) {
        this.details.put(key, value);
    }


}
