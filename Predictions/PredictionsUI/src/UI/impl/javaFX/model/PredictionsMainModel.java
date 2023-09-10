package UI.impl.javaFX.model;

import response.SimulatorResponse;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;

public class PredictionsMainModel {
    private SimulatorManager simulatorManager;
    public PredictionsMainModel() {
        this.simulatorManager = new SimulatorManagerImpl();
    }

    public void setEntityPopulation(String entityName, String population) {
        Integer populationNo = Integer.parseInt(population);
        simulatorManager.setEntityDefinitionPopulation(entityName, populationNo);
    }

    // Here we should implement any communication with the Engine !!!!


}
