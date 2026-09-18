package components;

public class Motherboard extends Component {
    private final String socket;

    public Motherboard(String id, double price, int watsConsumed, String socket) {
        super(id, "MOTHERBOARD", price, watsConsumed);
        this.socket = socket;
    }

    public String getSocket() {
        return socket;
    }
}
