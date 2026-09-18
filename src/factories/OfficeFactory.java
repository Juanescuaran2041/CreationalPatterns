// factories/OfficeFactory.java
package factories;

import components.*;

/**
 * Office family: socket LGA1700 — DIFFERENT from GamerFactory's AM5. A
 * Processor from here must never end up paired with a Motherboard from
 * the other factory; that is exactly what your R2 validation should prevent.
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
        return new Processor("CPU-OFF-01", 65, 620_000, "LGA1700");
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