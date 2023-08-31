package simulator.execution.manager.api;

import dto.EstablishedEnvironmentInfoDto;
import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.definition.world.WorldDefinition;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.result.api.SimulationResult;

public interface SimulationExecutionManager {
    SimulatorResponse establishSimulation(WorldDefinition worldDefinition);
    SimulatorResponse setSelectedEnvironmentVariablesValue(WorldDefinition worldDefinition, String propName, String type, String value);
    SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo();
    WorldInstance getWorldInstance();
    SimulatorResponse<SimulationEndDto> runSimulator(SimulationResult simulationResult);
}
