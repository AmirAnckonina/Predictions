package simulator.manager.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.world.World;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.manager.api.SimulationResult;

import java.util.*;
import java.util.Map.Entry;

public class SimulationResultImpl implements SimulationResult {
    private String simulationUuid;
    private WorldInstance worldInstance;
    private World worldDefinition;
    private Map<String, Integer> initializedStatus = new HashMap<>();
    private Long simulatorStartingTime;

    public SimulationResultImpl(String simulationGuid, WorldInstance worldInstance) {
        this.simulationUuid = simulationGuid;
        this.worldInstance = worldInstance;
        setInitializedEntityPopulation();
    }

    /*public SimulationResultImpl(WorldInstance worldInstance, String simulationID, World worldDefinition) {
        this.simulatorStartingTime = System.currentTimeMillis();
        this.worldDefinition = worldDefinition;
        this.worldInstance = worldInstance;
        this.simulationUuid = simulationID;
        //setInitializedEntityPopulation();
    }*/

    public void setInitializedEntityPopulation() {
        String entityName  = worldDefinition.getPrimaryEntity().getName();
        int population =  worldDefinition.getPrimaryEntity().getPopulation();
        initializedStatus.put(entityName, population);
    }

    @Override
    public String getSimulationUuid() {
        return this.simulationUuid;
    }

    @Override
    public Long getSimulationStartingTime() {
        return this.simulatorStartingTime;
    }

    @Override
    public PropertyInstance getEntityPropertyInstanceByPropertyName(String entityID, String propertyName) {
        return worldInstance.getPrimaryEntityInstances().get(Integer.valueOf(entityID)).getPropertyByName(propertyName);
    }

    @Override
    public Integer getNumOfInstancesOfEntityInitialized() {
        Integer returnValue = 0;
//        if(initializedStatus.containsKey(entityName)) {
//            returnValue = initializedStatus.get(entityName);
//        }
        Entry<String, Integer> firstEntry = initializedStatus.entrySet().iterator().next();
        Integer value = firstEntry.getValue();
        returnValue = value;

        return  returnValue;
    }

    @Override
    public Integer getNumOfInstancesOfEntityWhenSimulationStopped() {
        return worldInstance.getPrimaryEntityInstances().size();
    }

    @Override
    public List<String> getEntityPropertiesNames() {
        List<String> propertiesNames = new LinkedList<>();

        for (Map.Entry<String, AbstractPropertyDefinition> entry : this.worldDefinition.getPrimaryEntity().getProperties().entrySet()) {
            String key = entry.getKey();
            propertiesNames.add(key);
        }

        return propertiesNames;
    }

    @Override
    public EntityInstance getEntityByName(Integer entityID) {
        return worldInstance.getPrimaryEntityInstances().get(entityID);
    }

    @Override
    public List<EntityInstance> getEntities() {
        return worldInstance.getPrimaryEntityInstances();
    }
}