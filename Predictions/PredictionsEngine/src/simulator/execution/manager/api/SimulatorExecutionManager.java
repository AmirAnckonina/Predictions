package simulator.execution.manager.api;

import dto.SimulationEndDto;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.information.simulationDocument.api.SimulationDocument;

public interface SimulatorExecutionManager {

    WorldInstance getWorldInstance();
    void runSimulator(SimulationDocument simulationDocument);
    void initThreadPoolExecuter(Integer threadCount);
}
