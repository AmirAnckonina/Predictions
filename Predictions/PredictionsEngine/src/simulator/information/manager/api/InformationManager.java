package simulator.information.manager.api;

import dto.simulationInfo.SimulationDocumentInfoDto;
import dto.manualSetup.SimulationManualParamsDto;
import dto.simulationInfo.SimulationsStatusesOverviewDto;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.result.api.SimulationResult;

import java.util.Map;

public interface InformationManager {
    SimulationDocument createNewSimulationDocument(WorldDefinition worldDefinition, WorldInstance worldInstance);
    SimulationDocumentInfoDto getLatestSimulationDocumentInfoDto(String guid);
    Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String entityName, String simulationGuid);
    SimulationDocumentInfoDto getInitialSimulationDocumentInfoDto(String simulationGuid);
    SimulationDocument getSimulationDocumentByGuid(String guid);
    SimulationsStatusesOverviewDto collectAllSimulationsStatusesDto();
    SimulationResult getSimulationResultByGuid(String guid);
    SimulationManualParamsDto getSimulationManualParamsByGuid(String simulationGuid);
}
