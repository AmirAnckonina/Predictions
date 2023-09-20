package simulator.result.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.runner.utils.exceptions.TerminationReason;
import simulator.result.api.SimulationResult;

import java.util.*;
import java.util.Map.Entry;

public class SimulationResultImpl implements SimulationResult {
    private String simulationUuid;
    private WorldInstance worldInstance;
    private Map<String, Integer> initEntitiesPopulationStatus;
    private Map<String, Set<String>> entitiesPropertiesNames;
    private Long simulatorStartingTime;
    private TerminationReason terminationReason;

    public SimulationResultImpl(String simulationUuid, WorldInstance worldInstance, Map<String, Integer> initEntitiesPopulationStatus,
                                Long simulatorStartingTime) {
        this.simulationUuid = simulationUuid;
        this.worldInstance = worldInstance;
        this.initEntitiesPopulationStatus = initEntitiesPopulationStatus;
        this.simulatorStartingTime = simulatorStartingTime;
        entitiesPropertiesNames = new HashMap<>();
        worldInstance.getEntityDefinitionMap().forEach((entity,entityDefinition) ->
                entitiesPropertiesNames.put(entity, entityDefinition.getProperties().keySet()));
    }

    public TerminationReason getTerminationReason() {
        return terminationReason;
    }

    @Override
    public List<String> getAllPropertiesOfAllEntities() {
        List<String> res = new ArrayList<>();
        this.entitiesPropertiesNames.keySet().forEach(entityName -> res.addAll(getEntityPropertiesNames(entityName)));
        return res;
    }

    public void setTerminationReason(TerminationReason terminationReason) {
        this.terminationReason = terminationReason;
    }

    public void setInitializedEntityPopulation() {

        Map<String, List<EntityInstance>> entitiesInstances = this.worldInstance.getEntitiesInstances();
        entitiesInstances.forEach(
                (entName,entInstances) ->
                        initEntitiesPopulationStatus.put(entName, entInstances.size())
        );
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
    public PropertyInstance getEntityPropertyInstanceByPropertyName(String entityName, String entityID, String propertyName) {
        return worldInstance.
                getEntityInstancesByEntityName(entityName).
                get(Integer.valueOf(entityID)).
                getPropertyInstanceByName(propertyName);
    }

    @Override
    public Integer getNumOfInstancesOfEntityInitialized() {
        /**
         * Should be replaced with impl - for each entity , show the population.. ? TBD
         */

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
    public Integer getNumOfInstancesOfEntityWhenSimulationStopped(String entityName) {
        return worldInstance.getEntityInstancesByEntityName(entityName).size();
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
    public List<String> getEntityPropertiesNames(String entityName) {
        List<String> properties = new ArrayList<>();
        properties.addAll(this.entitiesPropertiesNames.get(entityName));
        return properties;
    }
/*
    @Override
    public EntityInstance getEntityByName(Integer entityID) {
        return worldInstance.getEntityInstances()ByEntityName().get(entityID);
    }*/

    @Override
    public Map<String, List<EntityInstance>> getEntities() {
        return worldInstance.getEntitiesInstances();
    }
}
