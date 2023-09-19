package simulator.information.simulationDocument.impl;

import dto.SimulationEndDto;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;

import java.util.*;

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
    public Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName) {
        Map<String,List<EntityInstance>> entitiesNames = simulationDocument.getSimulationResult().getEntities();
        List<String> propertiesByEntity = new ArrayList<>();
        entitiesNames.forEach((entityName, entityInstancesList) -> propertiesByEntity.
                addAll(simulationDocument.getSimulationResult().getEntityPropertiesNames(entityName)));

        Map<String,Integer> entityInstanceList = getAllEntityInstancesHasPropertyByPropertyName(
                propertyName);

        return entityInstanceList;
    }

    @Override
    public List<String> getAllEntitiesNames() {
        return new ArrayList<>(simulationDocument.getWorldInstance().getEntitiesInstances().keySet());
    }

    private Map<String,Integer> getAllEntityInstancesHasPropertyByPropertyName(
            String propertyName) {
        Set<String> entitiesNames = this.simulationDocument.getWorldInstance().getEntityDefinitionMap().keySet();
        List<EntityInstance> entityInstanceList = new ArrayList<>();
        entitiesNames.forEach(nameOfEntity -> entityInstanceList.addAll(this.simulationDocument.getSimulationResult()
                .getEntities().get(nameOfEntity)));
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
