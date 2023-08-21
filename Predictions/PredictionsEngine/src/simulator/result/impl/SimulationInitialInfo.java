package simulator.result.impl;

import simulator.execution.instance.world.api.WorldInstance;

import java.util.Set;

public class SimulationInitialInfo {

    private String SimulationGuid;
    private String primaryEntityName;
    private int primaryEntityPopulation;
    private Set<String> primaryEntityPropertiesName;

    private WorldInstance worldInstnce;

    public SimulationInitialInfo(String simulationGuid, String primaryEntityName, int primaryEntityPopulation, Set<String> primaryEntityPropertiesName, WorldInstance worldInstnce) {
        SimulationGuid = simulationGuid;
        this.primaryEntityName = primaryEntityName;
        this.primaryEntityPopulation = primaryEntityPopulation;
        this.primaryEntityPropertiesName = primaryEntityPropertiesName;
        this.worldInstnce = worldInstnce;
    }

    public String getSimulationGuid() {
        return SimulationGuid;
    }

    public String getPrimaryEntityName() {
        return primaryEntityName;
    }

    public int getPrimaryEntityPopulation() {
        return primaryEntityPopulation;
    }

    public Set<String> getPrimaryEntityPropertiesName() {
        return primaryEntityPropertiesName;
    }

    public WorldInstance getWorldInstnce() {
        return worldInstnce;
    }
}
