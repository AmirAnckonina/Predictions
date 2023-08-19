package simulator.execution.instance.entity.impl;

public class EntitiesResult {
    private Integer ID;
    private Integer numOfInstancesInitialized;
    private Integer numOfInstancesInEndOfSimulation;

    public EntitiesResult(Integer numOfInstancesInitialized, Integer numOfInstancesInEndOfSimulation) {
        this(null, numOfInstancesInitialized, numOfInstancesInEndOfSimulation);
    }

    public EntitiesResult(Integer ID, Integer numOfInstancesInitialized, Integer numOfInstancesInEndOfSimulation) {
        this.ID = ID;
        this.numOfInstancesInitialized = numOfInstancesInitialized;
        this.numOfInstancesInEndOfSimulation = numOfInstancesInEndOfSimulation;
    }

    public Integer getName() {
        return ID;
    }

    public void setName(Integer ID) {
        this.ID = ID;
    }

    public Integer getNumOfInstancesInitialized() {
        return numOfInstancesInitialized;
    }

    public void setNumOfInstancesInitialized(Integer numOfInstancesInitialized) {
        this.numOfInstancesInitialized = numOfInstancesInitialized;
    }

    public Integer getNumOfInstancesInEndOfSimulation() {
        return numOfInstancesInEndOfSimulation;
    }

    public void setNumOfInstancesInEndOfSimulation(Integer numOfInstancesInEndOfSimulation) {
        this.numOfInstancesInEndOfSimulation = numOfInstancesInEndOfSimulation;
    }
}
