package factories;

import components.*;

public class DesignFactory implements ComponentFactory {
    @Override
    public Case createCase() {
        return new Case("CASE_DESIGN01", 300);
    }

    @Override
    public Motherboard createMotherboard() {
        return new Motherboard("MB-DESIGN-01", 320, 25, "AM5");
    }

    @Override
    public Processor createProcessor() {
        return new Processor("CPU-DESIGN-01", 35,300, "AM5" );
    }

    @Override
    public PowerSupply createPowerSupply() {
        return new PowerSupply("PSU-DESIGN-01", 340,750);
    }

    @Override
    public RAM createRAM() {
        return new RAM("RAM-GAMER-01", 380_000, 10);
    }

    @Override
    public Storage createStorage() {
        return new Storage("SDD-DESIGN-01", 290, 6);
    }

    @Override
    public GPU createGPU() {
        return new GPU("GPU-GAMER-01", 380, 10);
    }

    @Override
    public Cooling createCooling() {
        return new Cooling("COOL-DESIGN-01", 350, 15);
    }

}
