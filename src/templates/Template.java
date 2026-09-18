package templates;

import components.Component;
import config.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

public class Template implements Prototype<Template> {
    private String name;
    private Configuration config;

    public Template(String name, Configuration config) {
        this.name = name;
        this.config = config;
    }

    @Override
    public Template clone() {
        Map<String, Component> clonedComponets = new LinkedHashMap<>(this.config.getComponentsByType());
        Configuration cloneConfig = new Configuration(this.config.getProductLine(),  clonedComponets);
        return new Template(this.name, cloneConfig);

    }
}
