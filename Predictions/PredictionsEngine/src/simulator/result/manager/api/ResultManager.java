package simulator.result.manager.api;
import dto.SimulationDocumentInfoDto;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.impl.EntitiesResult;

import simulator.information.tickDocument.api.TickDocument;
import simulator.result.api.SimulationResult;


import java.util.List;
import java.util.Map;

public interface ResultManager {

    List<EntitiesResult> getAllEntitiesExistBySimulationIndex(String entityName, String simulationID);
    List<EntityInstance> getAllEntitiesInstancesExistBySimulationIndex(String entityName, String simulationID);
    List<EntitiesResult> getAllEntitiesExistBySimulationIndex(String entityName, Integer simulationIndex);
    String getSimulatorStartingTimeInStringByID(String SimulationID);
    String getSimulatorStartingTimeInStringByIndex(int simulationIndex);
    List<SimulationResult> getSortedHistoricalSimulationsList();
    Map<String,Integer> getAllEntityInstancesHasPropertyByPropertyNameBySimulationIndex(String entityName,Integer simulationIndex, String PropertyName);
    List<String> getAllPropertiesOfEntityBySimulationID(String entityName, String simulationID);
    String getSimulatorIDByIndex(Integer simulationIndex);
    Map<String,Integer> getAllEntitiesHasPropertyByPropertyNameBySimulationID(
            String entityName, String uuid, String propertyName);
    List<String> getAllPropertiesOfEntityBySimulationIndex(String entityName, Integer simulationIndex);
    SimulationResult getSimulationResultBySimulationId(String simulationID);

    Map<Integer, Map<String, Integer>> createEntitiesPopulationOvertimeMap(Map<Integer, TickDocument> tickDocumentMap);
    Map<String, Integer> createInitialEntityPopulationMap(SimulationDocumentInfoDto initialSimulationDocumentInfoDto);
    Map<String, Map<String, Double>> createEntitiesPropertiesConsistencyMap(Map<String, List<EntityInstance>> entitiesInstances, Integer totalTicksCount);
    Map<String, Double> createPropertiesConsistencyMapForSingleEntity(List<EntityInstance> entityInstancesList, Integer totalTicksCount);
    Map<String, Map<String, Double>> createEntitiesNumericPropertyAverageMap(Map<String, List<EntityInstance>> entitiesInstances);

    Map<String, Double> createPropertiesNumericAverageMapForSingleEntity(List<EntityInstance> entityInstancesList);
}
