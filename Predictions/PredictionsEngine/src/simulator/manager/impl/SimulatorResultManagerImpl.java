package simulator.manager.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.manager.api.SimulationResult;
import simulator.manager.api.SimulatorResultManager;
import simulator.execution.instance.entity.impl.EntitiesResult;

import java.text.SimpleDateFormat;
import java.util.*;

public class SimulatorResultManagerImpl implements SimulatorResultManager {

    Map<String,SimulationResult> simulationResults;

    public SimulatorResultManagerImpl() {
        simulationResults = new HashMap<>();
    }


    public String getSimulatorStartingTimeInString(String simulationID){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
        String formattedTime = formatter.format(new Date(this.simulationResults.get(simulationID).getSimulationStartingTime()));

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
    public List<EntitiesResult> getAllEntitiesExist(String simulationID) {
        List<EntitiesResult> entitiesResultList = new ArrayList<>();
        EntitiesResult entitiesResult = new EntitiesResult(simulationResults.get(simulationID).getNumOfInstancesOfEntityInitialized(),
                simulationResults.get(simulationID).getNumOfInstancesOfEntityWhenSimulationStopped());
        entitiesResultList.add(0,entitiesResult);

        return entitiesResultList;
    }

    @Override
    public List<EntityInstance> getAllEntitiesHasPropertyByPropertyName(String PropertyName) {
        return null;
    }

    public boolean addSimulationResult(String simulationID, SimulationResult simulationResult) {
        boolean isSucceeded = false;
        try {
            this.simulationResults.put(simulationID, simulationResult);
        }catch (Exception e){
            isSucceeded = false;
        }

        return isSucceeded;
    }

}
