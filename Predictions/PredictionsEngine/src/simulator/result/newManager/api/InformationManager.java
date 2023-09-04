package simulator.result.newManager.api;

import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.result.api.SimulationResult;

public interface InformationManager {
    SimulationResult setNewSimulationInfo(WorldDefinition worldDefinition, WorldInstance worldInstance);
}
