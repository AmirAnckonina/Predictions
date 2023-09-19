package simulator.execution.manager.api;

import simulator.information.simulationDocument.api.SimulationDocument;

public interface SimulatorExecutionManager {

    void runSimulator(SimulationDocument simulationDocument);
    void initThreadPoolService(Integer threadCount);
    void stopSimulation(SimulationDocument simulationDocument);
    void pauseSimulation(SimulationDocument simulationDocument);
    void resumeSimulation(SimulationDocument simulationDocument);
}
