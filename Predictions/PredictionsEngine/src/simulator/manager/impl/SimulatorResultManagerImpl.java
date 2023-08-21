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



    private Integer lastSimulationResultIndex = 0;
    private Map<Integer,String> mapSimulationIndexToSimulationId;
    private Map<String,SimulationResult> simulationResults;


    public SimulatorResultManagerImpl() {
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
    public List<EntitiesResult> getAllEntitiesExistBySimulationIndex(String simulationID) {
        List<EntitiesResult> entitiesResultList = new ArrayList<>();
        EntitiesResult entitiesResult = new EntitiesResult(simulationResults.get(simulationID).getNumOfInstancesOfEntityInitialized(),
                simulationResults.get(simulationID).getNumOfInstancesOfEntityWhenSimulationStopped());
        entitiesResultList.add(0,entitiesResult);

        return entitiesResultList;
    }

    public List<EntitiesResult> getAllEntitiesExistBySimulationIndex(Integer simulationID) {
        List<EntitiesResult> entitiesResultList = new ArrayList<>();
        EntitiesResult entitiesResult = new EntitiesResult(simulationResults.get(simulationID).getNumOfInstancesOfEntityInitialized(),
                simulationResults.get(simulationID).getNumOfInstancesOfEntityWhenSimulationStopped());
        entitiesResultList.add(0,entitiesResult);

        return entitiesResultList;
    }

    @Override
    public Map<Integer,String> getAllEntitiesHasPropertyByPropertyNameBySimulationID(String uuid, String propertyName) {
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

    public Map<Integer,String> getAllEntitiesHasPropertyByPropertyNameBySimulationIndex(
            Integer simulationIndex, String propertyName) {
        List<EntityInstance> entityInstanceList = this.simulationResults
                .get(mapSimulationIndexToSimulationId.get(simulationIndex))
                .getEntities();
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
    public List<String> getAllPropertiesOfEntityBySimulationID(String simulationID) {
        return simulationResults.get(simulationID).getEntityPropertiesNames();
    }

    public List<String> getAllPropertiesOfEntityBySimulationIndex(Integer simulationIndex){
        return simulationResults
                .get(mapSimulationIndexToSimulationId.get(simulationIndex))
                .getEntityPropertiesNames();

    }

    public boolean addSimulationResult(String simulationID, SimulationResult simulationResult) {
        boolean isSucceeded = false;

        try {
            this.mapSimulationIndexToSimulationId.put(lastSimulationResultIndex, simulationID);
            this.lastSimulationResultIndex += 1;
        }  catch (Exception e) {
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
