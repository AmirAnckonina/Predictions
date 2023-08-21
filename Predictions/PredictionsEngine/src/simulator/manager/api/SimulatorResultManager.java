package simulator.manager.api;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.impl.EntitiesResult;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.result.impl.SimulationInitialInfo;

import java.util.List;
import java.util.Map;

public interface SimulatorResultManager {

    boolean addSimulationResult(String simulationID, SimulationResult simulationResult);
    boolean addSimulationResult(SimulationInitialInfo simulationInitInfo, SimulationResult simulationResult);

    String getSimulatorStartingTimeInString(String SimulationID);

    List<SimulationResult> getSortedHistoricalSimulationsList();

    public List<EntitiesResult> getAllEntitiesExist(String simulationID);

    Map<Integer,String> getAllEntitiesHasPropertyByPropertyName(String uuid, String PropertyName);

    List<String> getAllPropertiesOfEntity();

}
