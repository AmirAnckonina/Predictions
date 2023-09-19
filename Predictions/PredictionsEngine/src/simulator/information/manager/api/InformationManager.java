package simulator.information.manager.api;

import dto.SimulationDocumentInfoDto;
import dto.SimulationEndDto;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;

public interface InformationManager {
    SimulationDocument createNewSimulationDocument(WorldDefinition worldDefinition, WorldInstance worldInstance);

    SimulationDocumentFacade createSimulationDocumentFacade(SimulationDocument simulationDocument);
    SimulationDocumentInfoDto getSimulationDocumentInfo(String guid);
}
