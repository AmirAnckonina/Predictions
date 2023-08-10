package UI.impl;

import UI.api.UserInterface;
import dto.BuildSimulatorDto;
import simulator.manager.api.Simulator;
import simulator.manager.impl.SimulatorImpl;

public class ConsoleUI implements UserInterface {
    @Override
    public void runSimulatorUI() {
        Simulator simulator = new SimulatorImpl();
        BuildSimulatorDto buildSimulatorResult = simulator.buildSimulator("resources/ex1-cigarets.xml");
    }
}
