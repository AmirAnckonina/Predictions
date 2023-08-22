package simulator.result.api;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.runner.utils.exceptions.eTerminationReason;

import java.util.List;

public interface SimulationResult {

    String getSimulationUuid();
    Long getSimulationStartingTime();
    PropertyInstance getEntityPropertyInstanceByPropertyName(String entityName, String PropertyName);
    Integer getNumOfInstancesOfEntityInitialized();
    Integer getNumOfInstancesOfEntityWhenSimulationStopped();
    List<String> getEntityPropertiesNames();
    EntityInstance getEntityByName(Integer entityID);
    List<EntityInstance> getEntities();
    void setInitializedEntityPopulation();
    void setStartingTime(long startTimeInMilliSec);
    void setTerminationReason(eTerminationReason terminationReason);
    public eTerminationReason getTerminationReason();
}
