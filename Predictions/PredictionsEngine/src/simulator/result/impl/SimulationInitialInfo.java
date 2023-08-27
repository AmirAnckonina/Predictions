package simulator.result.impl;

import simulator.execution.instance.world.api.WorldInstance;

import java.util.Map;
import java.util.Set;

public class SimulationInitialInfo {

    private String SimulationGuid;

    private Map<String, Integer> entitiesPopulation;
    private Map<String, Set<String>> entitiesPropertiesNames;
    //private String primaryEntityName;
    //private int primaryEntityPopulation;
    //private Set<String> primaryEntityPropertiesName;
    private WorldInstance worldInstnce;

    /*public SimulationInitialInfo(String simulationGuid, String primaryEntityName, int primaryEntityPopulation, Set<String> primaryEntityPropertiesName, WorldInstance worldInstnce) {
        SimulationGuid = simulationGuid;
        this.primaryEntityName = primaryEntityName;
        this.primaryEntityPopulation = primaryEntityPopulation;
        this.primaryEntityPropertiesName = primaryEntityPropertiesName;
        this.worldInstnce = worldInstnce;
    }*/

    public SimulationInitialInfo(
            String simulationGuid,
            Map<String, Integer> entitiesPopulation,
            Map<String, Set<String>> entitiesPropertiesNames,
            WorldInstance worldInstance) {

        SimulationGuid = simulationGuid;
        this.entitiesPopulation = entitiesPopulation;
        this.entitiesPropertiesNames = entitiesPropertiesNames;
        this.worldInstnce = worldInstance;
    }

    public String getSimulationGuid() {
        return SimulationGuid;
    }

//    public String getPrimaryEntityName() {
//        return primaryEntityName;
//    }
//
//    public int getPrimaryEntityPopulation() {
//        return primaryEntityPopulation;
//    }
//
//    public Set<String> getPrimaryEntityPropertiesName() {
//        return primaryEntityPropertiesName;
//    }


    public Map<String, Integer> getEntitiesPopulation() {
        return entitiesPopulation;
    }

    public Map<String, Set<String>> getEntitiesPropertiesNames() {
        return entitiesPropertiesNames;
    }

    public WorldInstance getWorldInstnce() {
        return worldInstnce;
    }
}
