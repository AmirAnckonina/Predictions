package simulator.manager.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.manager.api.SimulationResult;
import simulator.manager.api.SimulatorResultManager;
import simulator.execution.instance.entity.impl.EntitiesResult;
import simulator.result.impl.SimulationInitialInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class SimulatorResultManagerImpl implements SimulatorResultManager {

    Map<String, SimulationResult> simulationResults;

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
    public Map<Integer,String> getAllEntitiesHasPropertyByPropertyName(String uuid, String propertyName) {
        List<EntityInstance> entityInstanceList = this.simulationResults.get(uuid).getEntities();
        Map<String, Integer> valueCountMap = new HashMap<>();
        Map<Integer, String> result = new HashMap<>();

        for (EntityInstance instance : entityInstanceList) {
            PropertyInstance propertyInstance = instance.getPropertyByName(propertyName);
            if (propertyInstance != null) {
                Object value = propertyInstance.getValue();
                valueCountMap.put("" + value, valueCountMap.getOrDefault("" + value, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : valueCountMap.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }

        return result;
    }

    @Override
    public List<String> getAllPropertiesOfEntity() {
        List<SimulationResult> simulationResultList = new ArrayList<>(simulationResults.values());
        return simulationResultList.get(0).getEntityPropertiesNames();
    }

    public boolean addSimulationResult(String simulationID, SimulationResult simulationResult) {
        boolean isSucceeded = false;

        try {
            this.simulationResults.put(simulationID, simulationResult);

        }  catch (Exception e){
            isSucceeded = false;
        }

        return isSucceeded;
    }

    @Override
    public boolean addSimulationResult(SimulationInitialInfo simulationInitInfo, SimulationResult simulationResult) {
        boolean isSucceeded = false;

        try {
            this.simulationResults.put(simulationInitInfo.getSimulationGuid(), simulationResult);

        }  catch (Exception e){
            isSucceeded = false;
        }

        return isSucceeded;
    }
}
