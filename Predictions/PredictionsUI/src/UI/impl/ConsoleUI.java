package UI.impl;

import UI.api.UserInterface;
import dto.BuildSimulatorDto;
import simulator.manager.api.SimulatorManager;
import simulator.manager.impl.SimulatorManagerImpl;

import java.util.Scanner;

public class ConsoleUI implements UserInterface {
    @Override
    public void runSimulatorUI() {
        SimulatorManager simulator = new SimulatorManagerImpl();
        //BuildSimulatorDto buildSimulatorResult = simulator.buildSimulationWorld("resources/ex1-cigarets.xml");
    }

    @Override
    public void buildSimulator() {
        //Scanner sc = new Scanner();

    }

    @Override
    public void runMenu() {

    }
}
