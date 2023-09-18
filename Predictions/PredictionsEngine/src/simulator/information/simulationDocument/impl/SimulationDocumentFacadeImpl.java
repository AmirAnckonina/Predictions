package simulator.information.simulationDocument.impl;

import dto.SimulationEndDto;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationDocumentFacadeImpl implements SimulationDocumentFacade {
    private SimulationDocument simulationDocument;
    private SimulationEndDto simulationEndDto;
    public SimulationDocumentFacadeImpl(SimulationDocument simulationDocument) {
        this.simulationDocument = simulationDocument;
    }

    @Override
    public Map<String, Integer> getMappedEntitiesToNumOfEntities() {
        Map<String, Integer> res = new HashMap<>();
        simulationDocument.getWorldInstance().getEntitiesInstances().forEach((entityName, entityInstances) -> res.put(entityName, entityInstances.size()));

        return res;
    }

    @Override
    public Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String entityName) {
        return null;

//        Map<String,List<EntityInstance>> entitiesNames = simulationDocument.getSimulationResult().getEntities();
//        simulationDocument.getSimulationResult().getEntityPropertiesNames(entityName);
//
//
//        Map<String,Integer> entityInstanceList =
//                this.simulatorResultManager
//                        .getAllEntityInstancesHasPropertyByPropertyNameBySimulationIndex(
//                                "smoker",
//                                this.simulationIndex,
//                                propertyNameChosen);
//
//        Map<String,Integer> entityInstanceList = getAllEntityInstancesHasPropertyByPropertyName(
//                                "smoker",
//                                propertyNameChosen);
//
//        return entityInstanceList;
    }

    public Map<String,Integer> getAllEntityInstancesHasPropertyByPropertyName(
            String entityName,
            String propertyName) {
        List<EntityInstance> entityInstanceList = this.simulationDocument.getSimulationResult()
                .getEntities().get(entityName);
        Map<String, Integer> valueCountMap = new HashMap<>();

        for (EntityInstance instance : entityInstanceList) {
            PropertyInstance propertyInstance = instance.getPropertyInstanceByName(propertyName);
            if (propertyInstance != null) {
                Object value = propertyInstance.getValue();
                valueCountMap.put("" + value, valueCountMap.getOrDefault("" + value, 0) + 1);
            }
        }

        return valueCountMap;
    }
}
