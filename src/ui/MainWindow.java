package ui;

import catalog.Catalog;
import components.Component;
import factories.GamerFactory;
import factories.OfficeFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("TechNova - Equipment Configurator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Catalog", createCatalogTab());
        tabs.addTab("Builder", createPlaceholderTab(
                "TODO: the Builder wizard goes here (RFE-05).\n"
                        + "One button per step: 'Add processor', 'Add motherboard', ...\n"
                        + "each one calling the corresponding ConfigurationBuilder method\n"
                        + "and refreshing a list/table with what has been added so far."));
        tabs.addTab("Templates", createPlaceholderTab(
                "TODO: list of saved templates + 'Clone' button (RFE-06)\n"
                        + "that calls template.clone() and shows the original and the copy side by side."));
        tabs.addTab("Reports", createPlaceholderTab(
                "TODO: metrics panel (RFE-10) + activity log (RFE-09)."));

        add(tabs);
    }

    private JComponent createCatalogTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JButton queryButton = new JButton("Query single catalog");

        // This is the UI -> backend connection: the button only calls the
        // public Singleton method and renders the result. There is no
        // business logic written inside this interface file.
        queryButton.addActionListener(event -> {
            Catalog catalog = Catalog.getInstance();
            Map<String, Component> components = catalog.getComponents();

            StringBuilder text = new StringBuilder();
            if (components.isEmpty()) {
                text.append("The catalog is empty. Register sample components first (RFE-11).\n");
            }
            for (Component component : components.values()) {
                int stock = catalog.checkStock(component.getId());
                text.append(component)
                        .append(" | stock: ")
                        .append(stock)
                        .append("\n");
            }
            resultArea.setText(text.toString());
        });

        panel.add(queryButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        return panel;
    }

    private JComponent createPlaceholderTab(String message) {
        JTextArea text = new JTextArea(message);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        return new JScrollPane(text);
    }

    public static void main(String[] args) {
        // Sample data so the Catalog tab does not start empty.
        Catalog catalog = Catalog.getInstance();
        catalog.registerComponent(5, new GamerFactory().createProcessor());
        catalog.registerComponent(8, new OfficeFactory().createProcessor());

        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
