package simulator.information.manager.api;

import dto.SimulationDocumentInfoDto;
import dto.SimulationManualParamsDto;
import dto.SimulationsStatusesOverviewDto;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;
import simulator.result.api.SimulationResult;

import java.util.Map;

public interface InformationManager {
    SimulationDocument createNewSimulationDocument(WorldDefinition worldDefinition, WorldInstance worldInstance);
    SimulationDocumentFacade createSimulationDocumentFacade(SimulationDocument simulationDocument);
    SimulationDocumentInfoDto getLatestSimulationDocumentInfoDto(String guid);
    Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String entityName, String simulationGuid);
    SimulationDocumentInfoDto getInitialSimulationDocumentInfoDto(String simulationGuid);
    SimulationDocument getSimulationDocumentByGuid(String guid);
    SimulationsStatusesOverviewDto collectAllSimulationsStatusesDto();
    SimulationResult getSimulationResultByGuid(String guid);
    SimulationManualParamsDto getSimulationManualParamsByGuid(String simulationGuid);
}
