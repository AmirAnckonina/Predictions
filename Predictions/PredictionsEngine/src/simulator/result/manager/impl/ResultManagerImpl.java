package simulator.result.manager.impl;

import dto.SimulationDocumentInfoDto;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.manager.exception.SimulationInformationException;
import simulator.information.tickDocument.api.TickDocument;
import simulator.result.api.SimulationResult;
import simulator.result.manager.api.ResultManager;
import simulator.execution.instance.entity.impl.EntitiesResult;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ResultManagerImpl implements ResultManager {



    private Integer lastSimulationResultIndex = 0;
    private Map<Integer,String> mapSimulationIndexToSimulationId;
    private Map<String,SimulationResult> simulationResults;


    public ResultManagerImpl() {
        simulationResults = new HashMap<>();
        mapSimulationIndexToSimulationId = new HashMap<>();
    }

    public String getSimulatorStartingTimeInStringByID(String simulationID){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
        String formattedTime = formatter.format(new Date(this.simulationResults.get(simulationID).getSimulationStartingTime()));

        return formattedTime;
    }

    public String getSimulatorStartingTimeInStringByIndex(int simulationIndex){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
        String formattedTime = formatter.format(new Date(this.simulationResults.get(mapSimulationIndexToSimulationId
                .get(simulationIndex)).getSimulationStartingTime()));

        return formattedTime;
    }

    @Override
    public List<SimulationResult> getSortedHistoricalSimulationsList() {
        List<SimulationResult> simulationResultList = new ArrayList<>(simulationResults.values());

        // Sort the list based on the creation time in descending order
        simulationResultList.sort(Comparator.comparingLong(SimulationResult::getSimulationStartingTime).reversed());

        return simulationResultList;
    }


    @Override
    public List<EntitiesResult> getAllEntitiesExistBySimulationIndex(String entityName, String simulationID) {
        List<EntitiesResult> entitiesResultList = new ArrayList<>();
        EntitiesResult entitiesResult = new EntitiesResult(simulationResults.get(simulationID).getNumOfInstancesOfEntityInitialized(),
                simulationResults.get(simulationID).getNumOfInstancesOfEntityWhenSimulationStopped(entityName));
        entitiesResultList.add(0,entitiesResult);

        return entitiesResultList;
    }

    @Override
    public List<EntityInstance> getAllEntitiesInstancesExistBySimulationIndex(String entityName, String simulationID) {
        List<EntityInstance> entityInstanceList = new ArrayList<>();
        entityInstanceList.addAll(simulationResults.get(simulationID).getEntities().get(entityName));

        return entityInstanceList;
    }

    @Override
    public List<EntitiesResult> getAllEntitiesExistBySimulationIndex(String entityName, Integer simulationIndex) {
        /**
         * Careful - we should impl according to the needs - get by entity name...
         */
        List<EntitiesResult> entitiesResultList = new ArrayList<>();
        EntitiesResult entitiesResult = new EntitiesResult(
                simulationResults
                        .get(mapSimulationIndexToSimulationId.get(simulationIndex))
                        .getNumOfInstancesOfEntityInitialized(),
                simulationResults
                        .get(mapSimulationIndexToSimulationId.get(simulationIndex))
                        .getNumOfInstancesOfEntityWhenSimulationStopped(entityName));
        entitiesResultList.add(0,entitiesResult);

        return entitiesResultList;
    }

    @Override
    public Map<String,Integer> getAllEntitiesHasPropertyByPropertyNameBySimulationID(
            String entityName,
            String uuid,
            String propertyName) {

        List<EntityInstance> entityInstancesList =
                this.simulationResults.get(uuid).getEntities().get(entityName);
        Map<String, Integer> valueCountMap = new HashMap<>();

        for (EntityInstance instance : entityInstancesList) {
            PropertyInstance propertyInstance = instance.getPropertyInstanceByName(propertyName);
            if (propertyInstance != null) {
                Object value = propertyInstance.getValue();
                valueCountMap.put("" + value, valueCountMap.getOrDefault("" + value, 0) + 1);
            }
        }

        return valueCountMap;
    }

    public Map<String,Integer> getAllEntityInstancesHasPropertyByPropertyNameBySimulationIndex(
            String entityName,
            Integer simulationIndex,
            String propertyName) {
        List<EntityInstance> entityInstanceList = this.simulationResults
                .get(mapSimulationIndexToSimulationId.get(simulationIndex))
                .getEntities().get(entityName);
        Map<String, Integer> valueCountMap = new HashMap<>();

        for (EntityInstance instance : entityInstanceList) {
            PropertyInstance propertyInstance = instance.getPropertyInstanceByName(propertyName);
            if (propertyInstance != null) {
                Object value = propertyInstance.getValue();
                valueCountMap.put("" + value, valueCountMap.getOrDefault("" + value, 0) + 1);
            }
        }

        return valueCountMap;
    }

    @Override
    public List<String> getAllPropertiesOfEntityBySimulationID(String entityName, String simulationID) {
        return simulationResults
                .get(simulationID)
                .getEntityPropertiesNames(entityName);
    }

    @Override
    public List<String> getAllPropertiesOfEntityBySimulationIndex(String entityName, Integer simulationIndex){
        return simulationResults
                .get(mapSimulationIndexToSimulationId.get(simulationIndex))
                .getEntityPropertiesNames(entityName);

    }

    @Override
    public String getSimulatorIDByIndex(Integer simulationIndex) {
        return this.mapSimulationIndexToSimulationId.get(simulationIndex);
    }

    public boolean addSimulationResult(String simulationID, SimulationResult simulationResult) {
        boolean isSucceeded = true;

        try {
            this.simulationResults.put(simulationID, simulationResult);
            this.mapSimulationIndexToSimulationId.put(lastSimulationResultIndex, simulationID);
            this.lastSimulationResultIndex += 1;
        }  catch (Exception e) {
            isSucceeded = false;
        }

        return isSucceeded;
    }

    public SimulationResult getSimulationResultBySimulationId(String simulationID){
        return this.simulationResults.get(simulationID);
    }

    @Override
    public Map<Integer, Map<String, Integer>> createEntitiesPopulationOvertimeMap(Map<Integer, TickDocument> tickDocumentMap) {
        Map<Integer, Map<String,Integer>> entitiesPopulationOvertimeMap =
                tickDocumentMap
                        .values()
                        .stream()
                        .collect(Collectors.toMap(
                                TickDocument::getTickNumber,
                                TickDocument::getEntitiesPopulationStatusMap
                        ));

        return entitiesPopulationOvertimeMap;
    }

    @Override
    public Map<String, Integer> createInitialEntityPopulationMap(SimulationDocumentInfoDto initialSimulationDocumentInfoDto) {
        return initialSimulationDocumentInfoDto.getInitialEntityPopulationMap();
    }

    @Override
    public Map<String, Map<String, Double>> createEntitiesPropertiesConsistencyMap(Map<String, List<EntityInstance>> entitiesInstances, Integer totalTicksCount,
                                                                                   Map<String, Double> entityInstanceAvrgMap) {

        Map<String, Map<String, Double>> entitiesPropertiesConsistencyMap = new HashMap<>();

        entitiesInstances.forEach((entityName, entityInstancesList) -> {
            System.out.println();
            entitiesPropertiesConsistencyMap.put(
                    entityName,
                    createPropertiesConsistencyMapForSingleEntity(entityInstancesList, totalTicksCount, entityInstanceAvrgMap,
                            entityName)
            );
        });

        return entitiesPropertiesConsistencyMap;
    }

    @Override
    public Map<String, Double> createPropertiesConsistencyMapForSingleEntity(List<EntityInstance> entityInstancesList, Integer totalTicksCount,
                                                                             Map<String, Double> entityInstanceAvrgMap, String entityName) {

        //Map<String,Double> unitePropertiesConsistencyMapOfAllInstances = new HashMap<>();
        List<Map<String, Double>> singleInstancePropertyConsistencyMapList = new ArrayList<>();

        entityInstancesList
                .forEach(entityInstance -> {
                    Map<String,Double> singlePropertyConsistencyMap =
                            entityInstance
                                    .getPropertiesMap()
                                    .values()
                                    .stream()
                                    .collect(Collectors.toMap(
                                            propertyInstance -> propertyInstance.getPropertyDefinition().getName(),
                                            propertyInstance -> Double.valueOf(totalTicksCount) / propertyInstance.getNumOfUpdates()));

                    singleInstancePropertyConsistencyMapList.add(singlePropertyConsistencyMap);
                });

        Map<String,Double> unitePropertiesConsistencyMapOfAllInstances = new HashMap<>();


        if (singleInstancePropertyConsistencyMapList.isEmpty()) {

            throw new SimulationInformationException("No instances from the current entity, couldn't create properties consistency map");
        } else {

        }

        singleInstancePropertyConsistencyMapList
                .get(0)
                .keySet()
                .forEach((propertyName) -> unitePropertiesConsistencyMapOfAllInstances.put(propertyName, 0.0));

        singleInstancePropertyConsistencyMapList
                .forEach((singleInstancePropertyConsistencyMap) -> {
                    singleInstancePropertyConsistencyMap
                            .keySet()
                            .forEach((propertyName) -> {
                                unitePropertiesConsistencyMapOfAllInstances.put(
                                        propertyName,
                                        unitePropertiesConsistencyMapOfAllInstances.get(propertyName) +
                                                singleInstancePropertyConsistencyMap.get(propertyName));
                            });
                });

        unitePropertiesConsistencyMapOfAllInstances
                .forEach((propertyName, propertyConsistencySum) -> {
                    unitePropertiesConsistencyMapOfAllInstances.put(
                            propertyName,
                            propertyConsistencySum / entityInstanceAvrgMap.get(entityName));
                });


        return unitePropertiesConsistencyMapOfAllInstances;
    }

}
