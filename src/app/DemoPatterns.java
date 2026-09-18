package app;

import catalog.Catalog;
import config.Configuration;
import config.ConfigurationBuilder;
import factories.ComponentFactory;
import factories.GamerFactory;
import factories.OfficeFactory;
import orders.CounterOrderGenerator;
import orders.Order;
import orders.OrderGenerator;
import orders.WarrantyOrderGenerator;
import templates.Template;

import java.util.LinkedHashMap;
import java.util.Map;

public class DemoPatterns {

    public static void main(String[] args) {
        System.out.println("--- 1) SINGLETON PATTERN ---" );
        Catalog catalog = Catalog.getInstance();
        Catalog catalog2 = Catalog.getInstance();
        System.out.println("Catalog1 == Catalog2 " + catalog.equals(catalog2));

        System.out.println("--- 2) ABSTRACT FACTORY ---" );
        ComponentFactory gamerFactory = new GamerFactory();
        ComponentFactory officeFactory = new OfficeFactory();

        System.out.println("Gamer processor:   " + gamerFactory.createProcessor());
        System.out.println("Gamer motherboard: socket " + gamerFactory.createMotherboard().getSocket());
        System.out.println("Office processor: " + officeFactory.createProcessor());
        System.out.println("Office motherboard: socket " + officeFactory.createMotherboard().getSocket());
        System.out.println("(Notice how each factory is internally consistent in its socket;");
        System.out.println(" mixing a processor from one with the motherboard from the other is exactly");
        System.out.println(" what your R2 validation must prevent.)");

        catalog.registerComponent(5, gamerFactory.createProcessor());
        catalog.registerComponent(3, gamerFactory.createMotherboard());
        System.out.println("Components in the single catalog: " + catalog);

        System.out.println("== 3) BUILDER ==");
        Configuration gamerConfiguration = new ConfigurationBuilder("GAMER", gamerFactory)
                .addProcessor()
                .addMotherboard()
                .addRAM()
                .addStorage()
                .addGPU()
                .addPowerSupply()
                .addCase()
                .build();
        System.out.println("Configuration built (" + gamerConfiguration.getProductLine() + "): "
                + gamerConfiguration.getComponentsByType().keySet());
        System.out.println("Total power consumption: "
                + gamerConfiguration.getTotalWattsConsumption() + "W");

        System.out.println();
        System.out.println("== 4) PROTOTYPE ==");
        Template streamerTemplate = new Template("Streamer", gamerConfiguration);
        Template clientCopy = streamerTemplate.clone();
        System.out.println("Is the copy the same Template object? " + (streamerTemplate == clientCopy));
        System.out.println("Do they share the same Configuration object? "
                + (streamerTemplate.getConfig() == clientCopy.getConfig()));
        System.out.println("(They must be different objects: that's why modifying the copy later");
        System.out.println(" cannot affect the original \"Streamer\" template, R5.)");

        System.out.println();
        System.out.println("== 5) FACTORY METHOD ==");
        OrderGenerator counterOrderGenerator = new CounterOrderGenerator();
        Map<String, String> counterData = new LinkedHashMap<>();
        counterData.put("customer", "Juan Perez");
        counterData.put("paymentMethod", "CARD");
        Order counterOrder = counterOrderGenerator.generateOrder(gamerConfiguration, counterData);
        System.out.println(counterOrder);

        OrderGenerator warrantyOrderGenerator = new WarrantyOrderGenerator();
        Map<String, String> warrantyData = new LinkedHashMap<>();
        warrantyData.put("originalOrderNumber", "ORD-0098");
        warrantyData.put("faultDescription", "The machine does not turn on");
        Order warrantyOrder = warrantyOrderGenerator.generateOrder(gamerConfiguration, warrantyData);
        System.out.println(warrantyOrder);

    }
}
