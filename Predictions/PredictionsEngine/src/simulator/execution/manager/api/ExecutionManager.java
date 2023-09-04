package simulator.execution.manager.api;

import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.result.api.SimulationResult;

public interface ExecutionManager {

    WorldInstance getWorldInstance();
    SimulatorResponse<SimulationEndDto> runSimulator(SimulationResult simulationResult);
}
