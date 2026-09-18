package catalog;

import java.util.HashMap;
import java.util.Map;
import components.Component;


public class Catalog {

    private final Map<String, Component> components;
    private final Map<String, Integer> stockById;

    private static Catalog instance;
    private Catalog(){
        components = new HashMap<String, Component>();
        stockById = new HashMap<String, Integer>();
    }


    //Singleton Pattern
    public static synchronized Catalog getInstance(){
        if(instance == null){
            instance = new Catalog();
        }
        return instance;
    }

    public Map<String, Component> getComponents(){
        return new HashMap<>(components);
    }

    public void registerComponent(int avaliableUnits, Component component){
        components.put(component.getId(), component);
        stockById.put(component.getId(), avaliableUnits);
    }

    public int checkStock(String id){
        return stockById.get(id);
    }

    public boolean sell(String id){
        int current = checkStock(id);

        if (current <= 0){
            return false;
        }
        stockById.put(id, stockById.get(id) - 1);
        return true;
    }
}
