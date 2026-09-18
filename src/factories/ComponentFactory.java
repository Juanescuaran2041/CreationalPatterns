package factories;
import components.*;

public interface ComponentFactory {

    Case createCase();

    Motherboard createMotherboard();

    PowerSupply createPowerSupply();

    Processor createProcessor();

    RAM createRAM();

    GPU createGPU();

    Storage createStorage();

    Cooling createCooling();
}
