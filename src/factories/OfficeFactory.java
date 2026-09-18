// factories/OfficeFactory.java
package factories;

import components.*;

/**
 * Familia Office: socket LGA1700 — DISTINTO al AM5 de GamerFactory. Un
 * Processor de aquí nunca debe terminar emparejado con un Motherboard de
 * la otra fábrica; eso es justamente lo que tu validación de R2 impediría.
 */
public class OfficeFactory implements ComponentFactory {

    @Override
    public Case createCase() {
        return new Case("CASE-OFF-01", 180_000);
    }

    @Override
    public Motherboard createMotherboard() {
        return new Motherboard("MB-OFF-01", 380_000, 15, "LGA1700");
    }

    @Override
    public Processor createProcessor() {
        return new Processor("CPU-OFF-01", 620_000, 65, "LGA1700");
    }

    @Override
    public RAM createRAM() {
        return new RAM("RAM-OFF-01", 180_000, 6);
    }

    @Override
    public Storage createStorage() {
        return new Storage("SSD-OFF-01", 160_000, 4);
    }

    @Override
    public GPU createGPU() {
        return new GPU("GPU-OFF-01-INTEGRATED", 0, 10);
    }

    @Override
    public PowerSupply createPowerSupply() {
        return new PowerSupply("PSU-OFF-01", 190_000, 350);
    }

    @Override
    public Cooling createCooling() {
        return new Cooling("COOL-OFF-01", 60_000, 5);
    }
}