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
        System.out.println("--- 1) SINGLETON PATTENR ---" );
        Catalog catalog = Catalog.getInstance();
        Catalog catalog2 = Catalog.getInstance();
        System.out.println("Catalog1 == Catalogo2 " + catalog.equals(catalog2));

        System.out.println("--- 2) AbstractFactory ---" );
        ComponentFactory gamerFactory = new GamerFactory();
        ComponentFactory officeFactory = new OfficeFactory();

        System.out.println("Procesador Gamer:   " + gamerFactory.createProcessor());
        System.out.println("Tarjeta madre Gamer: socket " + gamerFactory.createMotherboard().getSocket());
        System.out.println("Procesador Oficina: " + officeFactory.createProcessor());
        System.out.println("Tarjeta madre Oficina: socket " + officeFactory.createMotherboard().getSocket());
        System.out.println("(Nota como cada fabrica es internamente consistente en su socket;");
        System.out.println(" mezclar un procesador de una con la tarjeta madre de la otra es justo");
        System.out.println(" lo que tu validacion de R2 debe impedir.)");

        catalog.registerComponent(5, gamerFactory.createProcessor());
        catalog.registerComponent(3, gamerFactory.createMotherboard());
        System.out.println("Componentes en el catalogo unico: " + catalog);

        System.out.println("== 3) BUILDER ==");
        Configuration configuracionGamer = new ConfigurationBuilder("GAMER", gamerFactory)
                .addProcessor()
                .addMotherboard()
                .addCase()
                .build();
        System.out.println("Configuracion construida (" + configuracionGamer.getProductLine() + "): "
                + configuracionGamer.getComponentsByType().keySet());
        System.out.println("Consumo acumulado (parcial, faltan pasos por implementar): "
                + configuracionGamer.getComponentsByType() + "W");

        System.out.println();
        System.out.println("== 4) PROTOTYPE ==");
        Template plantillaStreamer = new Template("Streamer", configuracionGamer);
        Template copiaParaCliente = plantillaStreamer.clone();
        System.out.println("¿La copia es el mismo objeto Plantilla? " + (plantillaStreamer == copiaParaCliente));
        System.out.println("¿Comparten el mismo objeto Configuracion? "
                + (plantillaStreamer.getConfig() == copiaParaCliente.getConfig()));
        System.out.println("(Deben ser objetos distintos: por eso modificar la copia mas adelante");
        System.out.println(" no puede afectar la plantilla \"Streamer\" original, R5.)");

        System.out.println();
        System.out.println("== 5) FACTORY METHOD ==");
        OrderGenerator generadorMostrador = new CounterOrderGenerator();
        Map<String, String> datosCliente = new LinkedHashMap<>();
        datosCliente.put("cliente", "Juan Perez");
        datosCliente.put("formaPago", "TARJETA");
        Order ordenMostrador = generadorMostrador.generateOrder(configuracionGamer, datosCliente);
        System.out.println(ordenMostrador);

        OrderGenerator generadorGarantia = new WarrantyOrderGenerator();
        Map<String, String> datosGarantia = new LinkedHashMap<>();
        datosGarantia.put("numeroOrdenOriginal", "ORD-0098");
        datosGarantia.put("descripcionFalla", "La maquina no enciende");
        Order ordenGarantia = generadorGarantia.generateOrder(configuracionGamer, datosGarantia);
        System.out.println(ordenGarantia);

    }
}
