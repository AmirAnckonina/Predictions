package simulator.information.manager.impl;

import dto.SimulationDocumentInfoDto;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.manager.exception.SimulationInformationException;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;
import simulator.information.simulationDocument.impl.SimulationDocumentImpl;
import simulator.information.tickDocument.api.TickDocument;
import simulator.mainManager.utils.SimulatorUtils;
import simulator.result.manager.api.ResultManager;
import simulator.result.manager.impl.ResultManagerImpl;
import simulator.information.manager.api.InformationManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InformationManagerImpl implements InformationManager {
    private ResultManager simulatorResultManager;
    private Map<String, SimulationDocument> simulationDocumentMap;

    public InformationManagerImpl() {
        this.simulationDocumentMap = new ConcurrentHashMap<>();
        this.simulatorResultManager = new ResultManagerImpl();
    }

    @Override
    public SimulationDocument createNewSimulationDocument(WorldDefinition worldDefinition, WorldInstance worldInstance) {
        SimulationDocument simulationDocument;
        String guid = SimulatorUtils.getGUID();
        simulationDocument = new SimulationDocumentImpl(guid, worldInstance);
        this.simulationDocumentMap.put(guid, simulationDocument);
        return simulationDocument;
    }

    @Override
    public SimulationDocumentFacade createSimulationDocumentFacade(SimulationDocument simulationDocument) {
//        SimulationDocumentFacadeImpl simulationDocumentFacade = new SimulationDocumentFacadeImpl(simulationDocument);
        throw new SimulationInformationException("not impl creayeSimulationDocument");
    }

    @Override
    public SimulationDocumentInfoDto getLatestSimulationDocumentInfoDto(String guid) {

        SimulationDocument simulationDoc = this.simulationDocumentMap.get(guid);
        TickDocument latestTickDoc = simulationDoc.getLatestTickDocument();
        Map<String, Integer> entityPopulationMap = new HashMap<>();
        latestTickDoc
                .getEntitiesInstancesMap()
                .forEach((entityName, entityInstances) -> entityPopulationMap.put(entityName, entityInstances.size()));

        return new SimulationDocumentInfoDto(
                simulationDoc.getSimulationGuid(),
                simulationDoc.getSimulationStatus(),
                simulationDoc.getLatestTickDocument().getTickNumber(),
                simulationDoc.getLatestTickDocument().getTimePassedInSeconds(),
                entityPopulationMap,
                simulationDoc.getInitialSimulationDocumentInfoDto().getInitialEntityPopulationMap()
        );
    }

    @Override
    public SimulationDocumentInfoDto getInitialSimulationDocumentInfoDto(String simulationGuid) {
        return this.simulationDocumentMap.get(simulationGuid).getInitialSimulationDocumentInfoDto();
    }

    @Override
    public SimulationDocument getSimulationDocumentByGuid(String guid) {
        throw new SimulationInformationException("Please add sync here !!!!!!!");
        //return this.simulationDocumentMap.get(guid);
    }

    @Override
    public Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String simulationGuid) {
        Map<String,List<EntityInstance>> entitiesNames = simulationDocumentMap.get(simulationGuid).getSimulationResult().getEntities();
        List<String> propertiesByEntity = new ArrayList<>();
        entitiesNames.forEach((entityName, entityInstancesList) -> propertiesByEntity.
                addAll(simulationDocumentMap.get(simulationGuid).getSimulationResult().getEntityPropertiesNames(entityName)));

        Map<String,Integer> entityInstanceList = getAllEntityInstancesHasPropertyByPropertyName(
                propertyName, simulationGuid);

        return entityInstanceList;
    }

    private Map<String,Integer> getAllEntityInstancesHasPropertyByPropertyName(
            String propertyName, String simulationGuid) {
        Set<String> entitiesNames = this.simulationDocumentMap.get(simulationGuid).getWorldInstance().getEntityDefinitionMap().keySet();
        List<EntityInstance> entityInstanceList = new ArrayList<>();
        entitiesNames.forEach(nameOfEntity -> entityInstanceList.addAll(this.simulationDocumentMap.get(simulationGuid).getSimulationResult()
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
