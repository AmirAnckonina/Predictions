package simulator.result.api;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.runner.utils.exceptions.eTerminationReason;

import java.util.List;
import java.util.Map;

public interface SimulationResult {

    String getSimulationUuid();
    Long getSimulationStartingTime();
    PropertyInstance getEntityPropertyInstanceByPropertyName(String entityName, String entityID, String propertyName);
    Integer getNumOfInstancesOfEntityInitialized();
    Integer getNumOfInstancesOfEntityWhenSimulationStopped(String entityName);
    List<String> getEntityPropertiesNames(String entityName);
    //EntityInstance getEntityByName(Integer entityID);
    public Map<String, List<EntityInstance>> getEntities();
    void setInitializedEntityPopulation();
    void setStartingTime(long startTimeInMilliSec);
    void setTerminationReason(eTerminationReason terminationReason);
    public eTerminationReason getTerminationReason();
}
