package simulator.result.newManager;

import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.result.api.SimulationResult;

public interface SimulationInformationManager {
    SimulationResult setNewSimulationInfo(WorldDefinition worldDefinition, WorldInstance worldInstance);
}
