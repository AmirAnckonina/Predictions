package UI.impl;

import UI.api.UserInterface;
import dto.BuildSimulatorDto;
import simulator.manager.api.SimulatorManager;
import simulator.manager.impl.SimulatorManagerImpl;

public class ConsoleUI implements UserInterface {
    @Override
    public void runSimulatorUI() {
        SimulatorManager simulator = new SimulatorManagerImpl();
        BuildSimulatorDto buildSimulatorResult = simulator.buildSimulator("resources/ex1-cigarets.xml");
    }
}
