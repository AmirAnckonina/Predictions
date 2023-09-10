package simulator.execution.manager.api;

import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.result.api.SimulationResult;

public interface ExecutionManager {

    WorldInstance getWorldInstance();
    SimulationEndDto runSimulator(SimulationDocument simulationDocument);
}
