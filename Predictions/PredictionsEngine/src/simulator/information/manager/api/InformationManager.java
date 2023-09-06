package simulator.information.manager.api;

import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.simulationDocument.api.SimulationDocument;

public interface InformationManager {
    SimulationDocument createNewSimulationDocument(WorldDefinition worldDefinition, WorldInstance worldInstance);

}