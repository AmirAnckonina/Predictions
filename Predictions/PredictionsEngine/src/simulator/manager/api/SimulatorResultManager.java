package simulator.manager.api;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.impl.EntitiesResult;

import java.util.List;

public interface SimulatorResultManager {

    boolean addSimulationResult(String simulationID, SimulationResult simulationResult);

    String getSimulatorStartingTimeInString(String SimulationID);

    List<SimulationResult> getSortedHistoricalSimulationsList();

    public List<EntitiesResult> getAllEntitiesExist(String simulationID);

    List<EntityInstance> getAllEntitiesHasPropertyByPropertyName(String PropertyName);

}
