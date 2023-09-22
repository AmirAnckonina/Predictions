package simulator.result.api;

import dto.PropertiesAvgConsistencyDto;
import dto.PropertiesConsistencyDto;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.runner.utils.exceptions.TerminationReason;

import java.util.List;
import java.util.Map;

public interface SimulationResult {
    String getSimulationGuid();
    Long getSimulationStartingTime();
    PropertyInstance getEntityPropertyInstanceByPropertyName(String entityName, String entityID, String propertyName);
    Integer getNumOfInstancesOfEntityInitialized();
    Integer getNumOfInstancesOfEntityWhenSimulationStopped(String entityName);
    List<String> getEntityPropertiesNames(String entityName);
    //EntityInstance getEntityByName(Integer entityID);
    public Map<String, List<EntityInstance>> getEntities();
    void setInitializedEntityPopulation();
    void setStartingTime(long startTimeInMilliSec);
    void setTerminationReason(TerminationReason terminationReason);

    void setInitialEntitiesPopulationStatus(Map<String, Integer> initEntitiesPopulationStatus);

    void setEntitiesPopulationOvertimeMap(Map<Integer, Map<String, Integer>> entitiesPopulationOvertimeMap);

    void setEntitiesPropertiesConsistencyMap(Map<String, Map<String, Double>> entitiesPropertiesConsistencyMap);
    PropertiesConsistencyDto getEntitiesPropertiesConsistencyMap();
    PropertiesAvgConsistencyDto getEntitiesPopulationOvertimeDto();

    void setEntityNumericPropertyAverageMap(Map<String, Map<String, Double>> entityNumericPropertyAverageMap);

    TerminationReason getTerminationReason();

    Map<Integer, Map<String, Integer>> getEntitiesPopulationOvertimeMap();

    List<String> getAllPropertiesOfAllEntities();
    Integer getTotalTicksCount();
}
