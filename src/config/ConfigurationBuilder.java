package config;
import components.*;

import java.util.LinkedHashMap;
import java.util.Map;
import factories.ComponentFactory;

public class ConfigurationBuilder {
    private final Map<String, Component> components = new LinkedHashMap<>();
    private final String productLine;
    private final ComponentFactory factory;

    public ConfigurationBuilder(String productLine, ComponentFactory factory) {
        this.productLine = productLine;
        this.factory = factory;
    }


    public ConfigurationBuilder addProcessor(){
        components.put("PROCESSOR",  factory.createProcessor());
        return this;
    }

    public ConfigurationBuilder addCooling(){
        components.put("COOLING",  factory.createCooling());
        return this;
    }

    public ConfigurationBuilder addStorage(){
        components.put("STORAGE",  factory.createStorage());
        return this;
    }

    public ConfigurationBuilder addGPU(){
        components.put("GPU",  factory.createGPU());
        return this;
    }

    public ConfigurationBuilder addMotherboard(){
        components.put("MOTHERBOARD",  factory.createMotherboard());
        return this;
    }

    public ConfigurationBuilder addRAM(){
        components.put("RAM",  factory.createRAM());
        return this;
    }

    public ConfigurationBuilder addPowerSupply(){
        components.put("POWER_SUPPLY", factory.createPowerSupply());
        return this;
    }

    public ConfigurationBuilder addCase(){
        components.put("CASE",  factory.createCase());
        return this;
    }

    public Configuration build(){
        Processor processor = (Processor) components.get("PROCESSOR");
        Motherboard motherboard = (Motherboard) components.get("MOTHERBOARD");

        if (processor != null && motherboard != null) {
            if(!(processor.getSocket().equals(motherboard.getSocket()))) {
                throw new RuntimeException("Processor and Motherboard are not the same");
            }

        }

        PowerSupply powerSupply = (PowerSupply) components.get("POWER_SUPPLY");
        if (powerSupply != null) {
            int totalWats = 0;

            for (Component component : components.values()) {
                totalWats += component.getWatsConsumed();
            }

            if (totalWats > powerSupply.getCapacityWats()) {
                throw new RuntimeException("Wats consumed exceeds power supply");
            }
        }

        //Validate that all componets are created
        String[] requiredTypes = { "PROCESSOR", "MOTHERBOARD", "RAM", "STORAGE", "GPU", "POWER_SUPPLY", "CASE"};

        for(String type : requiredTypes) {
            if (!components.containsKey(type)) {
                throw new RuntimeException("Component " + type + " does not exist");
            }
        }

        if (productLine.equals("DESIGN") && !components.containsKey("COOLING")) {
            throw new RuntimeException("Design line requires a COOLING component");
        }

        return new Configuration(productLine, components);
    }
}
