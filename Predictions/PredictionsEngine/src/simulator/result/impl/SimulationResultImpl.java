package simulator.result.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.runner.utils.exceptions.eTerminationReason;
import simulator.result.api.SimulationResult;

import java.util.*;
import java.util.Map.Entry;

public class SimulationResultImpl implements SimulationResult {
    private String simulationUuid;
    private WorldInstance worldInstance;
    private Map<String, Integer> initEntitiesPopulationStatus;
    private Set<String> primaryEntityPropertiesName;
    private Long simulatorStartingTime;
    private eTerminationReason terminationReason;

    public SimulationResultImpl(SimulationInitialInfo simulationInitialInfo) {
        this.simulationUuid = simulationInitialInfo.getSimulationGuid();
        this.initEntitiesPopulationStatus = new HashMap<>();
        this.initEntitiesPopulationStatus.put(
                simulationInitialInfo.getPrimaryEntityName(),
                simulationInitialInfo.getPrimaryEntityPopulation()
        );
        this.primaryEntityPropertiesName = simulationInitialInfo.getPrimaryEntityPropertiesName();
        this.worldInstance = simulationInitialInfo.getWorldInstnce();
    }

    public eTerminationReason getTerminationReason() {
        return terminationReason;
    }

    public void setTerminationReason(eTerminationReason terminationReason) {
        this.terminationReason = terminationReason;
    }

    public void setInitializedEntityPopulation() {
        String entityName  = worldInstance.getPrimaryEntityName();
        int population =  worldInstance.getPrimaryEntityInstances().size();
        initEntitiesPopulationStatus.put(entityName, population);
    }

    @Override
    public void setStartingTime(long startTimeInMilliSec) {
        this.simulatorStartingTime = startTimeInMilliSec;
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
        Entry<String, Integer> firstEntry = initEntitiesPopulationStatus.entrySet().iterator().next();
        Integer value = firstEntry.getValue();
        returnValue = value;

        return  returnValue;
    }

    @Override
    public Integer getNumOfInstancesOfEntityWhenSimulationStopped() {
        return worldInstance.getPrimaryEntityInstances().size();
    }

//    @Override
//    public List<String> getEntityPropertiesNames() {
//        List<String> propertiesNames = new LinkedList<>();
//
//        for (Map.Entry<String, AbstractPropertyDefinition> entry :
//                this.worldDefinition.getPrimaryEntity().getProperties().entrySet()) {
//            String key = entry.getKey();
//            propertiesNames.add(key);
//        }
//
//        return propertiesNames;
//    }

    @Override
    public List<String> getEntityPropertiesNames() {
        List<String> properties = new ArrayList<>();
        properties.addAll(this.primaryEntityPropertiesName);
        return properties;
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
