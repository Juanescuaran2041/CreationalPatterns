package factories;

import components.*;

public class GamerFactory implements ComponentFactory {

    @Override
    public Case createCase() {
        return new Case("CASE-GAMER-01", 320_000);
    }

    @Override
    public Motherboard createMotherboard() {
        return new Motherboard("MB-GAMER-01", 650_000, 25, "AM5");
    }

    @Override
    public Processor createProcessor() {
        return new Processor("CPU-GAMER-01", 105, 1_450_000, "AM5");
    }

    @Override
    public RAM createRAM() {
        return new RAM("RAM-GAMER-01", 380_000, 10);
    }

    @Override
    public Storage createStorage() {
        return new Storage("SSD-GAMER-01", 290_000, 6);
    }

    @Override
    public GPU createGPU() {
        return new GPU("GPU-GAMER-01", 2_800_000, 320);
    }

    @Override
    public PowerSupply createPowerSupply() {
        return new PowerSupply("PSU-GAMER-01", 480_000, 750);
    }

    @Override
    public Cooling createCooling() {
        return new Cooling("COOL-GAMER-01", 350_000, 15);
    }
}