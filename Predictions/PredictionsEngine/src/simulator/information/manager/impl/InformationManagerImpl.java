package simulator.information.manager.impl;

import dto.SimulationDocumentInfoDto;
import dto.SimulationManualParamsDto;
import dto.SimulationsStatusesOverviewDto;
import enums.OverviewSimulationStatus;
import enums.SimulationStatus;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.manager.exception.SimulationInformationException;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.impl.SimulationDocumentImpl;
import simulator.information.tickDocument.api.TickDocument;
import simulator.mainManager.utils.SimulatorUtils;
import simulator.result.api.SimulationResult;
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
    public SimulationDocumentInfoDto getLatestSimulationDocumentInfoDto(String guid) {
        // Why latest? this return SimulationDocumentInfoDto by guid which might not be the latest @ AmirAnko

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
        return this.simulationDocumentMap.get(guid);
    }

    @Override
    public SimulationsStatusesOverviewDto collectAllSimulationsStatusesDto() {

        Map<OverviewSimulationStatus, Integer> simulationsStatusesOverviewMap = new HashMap<>();

         Long currentlyRunning =
                this.simulationDocumentMap
                        .values()
                        .stream()
                        .filter((simDoc) -> simDoc.getSimulationStatus() == SimulationStatus.RUNNING)
                        .count();
        simulationsStatusesOverviewMap.put(OverviewSimulationStatus.RUNNING, currentlyRunning.intValue());

        Long currentlyWaiting =
                this.simulationDocumentMap
                        .values()
                        .stream()
                        .filter((simDoc) ->
                                simDoc.getSimulationStatus() == SimulationStatus.PAUSED
                                        || simDoc.getSimulationStatus() == SimulationStatus.READY)
                        .count();
        simulationsStatusesOverviewMap.put(OverviewSimulationStatus.WAITING, currentlyWaiting.intValue());

        Long finished =
                this.simulationDocumentMap
                        .values()
                        .stream()
                        .filter((simDoc) ->
                                simDoc.getSimulationStatus() == SimulationStatus.STOPPED
                                        || simDoc.getSimulationStatus() == SimulationStatus.COMPLETED)
                        .count();
        simulationsStatusesOverviewMap.put(OverviewSimulationStatus.FINISHED, finished.intValue());

        return new SimulationsStatusesOverviewDto(simulationsStatusesOverviewMap);
    }

    @Override
    public SimulationResult getSimulationResultByGuid(String guid) {
        return simulationDocumentMap.get(guid).getSimulationResult();
    }

    @Override
    public SimulationManualParamsDto getSimulationManualParamsByGuid(String simulationGuid) {
        return this.simulationDocumentMap.get(simulationGuid).getSimulationManualParamsDto();
    }

    @Override
    public Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String entityName, String simulationGuid) {
//        Map<String,List<EntityInstance>> entitiesNames = simulationDocumentMap.get(simulationGuid).getSimulationResult().getEntities();
//        List<String> propertiesByEntity = new ArrayList<>();
//        propertiesByEntity.add();
//        entitiesNames.forEach((entityName, entityInstancesList) -> propertiesByEntity.
//                addAll(simulationDocumentMap.get(simulationGuid).getSimulationResult().getEntityPropertiesNames(entityName)));
//
//        Map<String,Integer> entityInstanceList = getAllEntityInstancesHasPropertyByPropertyName(
//                propertyName, simulationGuid);
//
//        return entityInstanceList;
        return getAllEntityInstancesHasPropertyByPropertyName(propertyName, entityName, simulationGuid);
    }



    private Map<String,Integer> getAllEntityInstancesHasPropertyByPropertyName(
            String propertyName, String entityName, String simulationGuid) {
        List<EntityInstance> entities = getSimulationResultByGuid(simulationGuid).getEntities().get(entityName);
        Map<String, Integer> valueCountMap = new HashMap<>();

        for (EntityInstance instance : entities) {
            PropertyInstance propertyInstance = instance.getPropertyInstanceByName(propertyName);
            if (propertyInstance != null) {
                Object value = propertyInstance.getValue();
                valueCountMap.put("" + value, valueCountMap.getOrDefault("" + value, 0) + 1);
            }
        }

        return valueCountMap;
    }
}
