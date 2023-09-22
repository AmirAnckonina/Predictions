package simulator.result.impl;

import dto.PropertiesAvgConsistencyDto;
import dto.PropertiesConsistencyDto;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.runner.utils.exceptions.TerminationReason;
import simulator.result.api.SimulationResult;

import java.util.*;
import java.util.Map.Entry;

public class SimulationResultImpl implements SimulationResult {
    private String simulationGuid;
    private WorldInstance worldInstance;
    private Map<String, Integer> initEntitiesPopulationStatus;
    private Map<String, Set<String>> entitiesPropertiesNames;
    private Map<Integer, Map<String, Integer>> entitiesPopulationOvertimeMap;
    private Map<String, Map<String, Double>> entitiesPropertiesConsistencyMap;
    private Map<String, Map<String, Double>> entityNumericPropertyAverageMap;
    private Long simulatorStartingTime;
    private Integer totalTicksCount;
    private Long totalTimeInSeconds;
    private TerminationReason terminationReason;

    public SimulationResultImpl(
            String simulationGuid,
            WorldInstance worldInstance,
            Long simulatorStartingTime,
            Integer totalTicksCount,
            Long totalTimeInSeconds) {

        this.simulationGuid = simulationGuid;
        this.worldInstance = worldInstance;
        this.simulatorStartingTime = simulatorStartingTime;
        this.totalTicksCount = totalTicksCount;
        this.totalTimeInSeconds = totalTimeInSeconds;

        entitiesPropertiesNames = new HashMap<>();
        worldInstance.getEntityDefinitionMap().forEach((entity,entityDefinition) ->
                entitiesPropertiesNames.put(entity, entityDefinition.getProperties().keySet()));
    }

    @Override
    public void setInitialEntitiesPopulationStatus(Map<String, Integer> initEntitiesPopulationStatus) {
        this.initEntitiesPopulationStatus = initEntitiesPopulationStatus;
    }

    @Override
    public void setEntitiesPopulationOvertimeMap(Map<Integer, Map<String, Integer>> entitiesPopulationOvertimeMap) {
        this.entitiesPopulationOvertimeMap = entitiesPopulationOvertimeMap;
    }

    @Override
    public void setEntitiesPropertiesConsistencyMap(Map<String, Map<String, Double>> entitiesPropertiesConsistencyMap) {
        this.entitiesPropertiesConsistencyMap = entitiesPropertiesConsistencyMap;
    }

    @Override
    public PropertiesConsistencyDto getEntitiesPropertiesConsistencyMap() {
        PropertiesConsistencyDto res = new PropertiesConsistencyDto(entitiesPropertiesConsistencyMap);
        return res;
    }

    @Override
    public PropertiesAvgConsistencyDto getEntitiesPropertiesAvgDto() {
        PropertiesConsistencyDto res = new PropertiesConsistencyDto(this.entityNumericPropertyAverageMap);
        return null;
    }

    @Override
    public void setEntityNumericPropertyAverageMap(Map<String, Map<String, Double>> entityNumericPropertyAverageMap) {
        this.entityNumericPropertyAverageMap = entityNumericPropertyAverageMap;
    }

    @Override
    public TerminationReason getTerminationReason() {
        return terminationReason;
    }

    @Override
    public Map<Integer, Map<String, Integer>> getEntitiesPopulationOvertimeMap() {
        return entitiesPopulationOvertimeMap;
    }

    @Override
    public List<String> getAllPropertiesOfAllEntities() {
        List<String> res = new ArrayList<>();
        this.entitiesPropertiesNames.keySet().forEach(entityName -> res.addAll(getEntityPropertiesNames(entityName)));
        return res;
    }

    @Override
    public Integer getTotalTicksCount() {
        return this.totalTicksCount;
    }

    @Override
    public void setTerminationReason(TerminationReason terminationReason) {
        this.terminationReason = terminationReason;
    }

    @Override
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
    public String getSimulationGuid() {
        return this.simulationGuid;
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
