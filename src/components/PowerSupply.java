package components;

public class PowerSupply extends Component {
    private final int capacityWats;

    public PowerSupply(String id, double price, int  capacityWats) {
        super(id, "POWER_SUPPLY", price, 0);
        this.capacityWats = capacityWats;
    }

    public int getCapacityWats() {
        return capacityWats;
    }
}

