package components;

public class Processor extends Component {
    private final String socket;

    public Processor(String id, int watsConsumed, double price, String socket ) {
        super(id, "PROCESSOR", price, watsConsumed);
        this.socket = socket;
    }

    public String getSocket() {
        return socket;
    }
}
