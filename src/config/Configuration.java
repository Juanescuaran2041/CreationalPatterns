package config;
import components.Component;

import java.util.LinkedHashMap;
import java.util.Map;


public class Configuration {

    private final String productLine;
    private final Map<String, Component> componentsByType;

    public Configuration(String productLine, Map<String, Component> componentsByType) {
        this.productLine = productLine;

        this.componentsByType = new LinkedHashMap<>(componentsByType);
    }

    public String getProductLine() {
        return productLine;
    }

    public Map<String, Component> getComponentsByType() {
        return new LinkedHashMap<>(componentsByType);
    }

    public int getTotalWattsConsumption() {
        return componentsByType.values().stream()
                .mapToInt(Component::getWatsConsumed)
                .sum();
    }

    public double getTotalPrice() {
        return componentsByType.values().stream()
                .mapToDouble(Component::getPrice)
                .sum();
    }
}