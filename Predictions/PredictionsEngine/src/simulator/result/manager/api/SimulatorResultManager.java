package simulator.result.manager.api;
import simulator.execution.instance.entity.impl.EntitiesResult;

import simulator.result.api.SimulationResult;
import simulator.result.impl.SimulationInitialInfo;


import java.util.List;
import java.util.Map;

public interface SimulatorResultManager {

    boolean addSimulationResult(String simulationID, SimulationResult simulationResult);
    boolean addSimulationResult(SimulationInitialInfo simulationInitInfo, SimulationResult simulationResult);

    String getSimulatorStartingTimeInStringByID(String SimulationID);

    String getSimulatorStartingTimeInStringByIndex(int simulationIndex);

    List<SimulationResult> getSortedHistoricalSimulationsList();

    public List<EntitiesResult> getAllEntitiesExistBySimulationIndex(String simulationID);
    public List<EntitiesResult> getAllEntitiesExistBySimulationIndex(Integer simulationIndex);

    Map<String,Integer> getAllEntitiesHasPropertyByPropertyNameBySimulationID(String uuid, String PropertyName);
    Map<String,Integer> getAllEntitiesHasPropertyByPropertyNameBySimulationIndex(Integer simulationIndex, String PropertyName);

    List<String> getAllPropertiesOfEntityBySimulationID(String simulationID);
    List<String> getAllPropertiesOfEntityBySimulationIndex(Integer simulationID);

    String getSimulatorIDByIndex(Integer simulationIndex);


}
