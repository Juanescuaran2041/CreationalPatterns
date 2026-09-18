package components;

public abstract class Component {
    private final String id;
    private final String type;
    private final double price;
    private final int watsConsumed;

    protected Component(String id, String type, double price, int watsConsumed) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.watsConsumed = watsConsumed;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getWatsConsumed() {
        return watsConsumed;
    }

}
