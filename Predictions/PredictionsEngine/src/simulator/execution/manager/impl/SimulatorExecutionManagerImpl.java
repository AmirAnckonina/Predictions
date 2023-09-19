package simulator.execution.manager.impl;

import dto.SimulationEndDto;
import enums.SimulationStatus;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.manager.api.SimulatorExecutionManager;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.runner.api.SimulationRunner;
import simulator.runner.impl.SimulationRunnerImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulatorExecutionManagerImpl implements SimulatorExecutionManager {
    private ExecutorService simulationExecutorService;

    public SimulatorExecutionManagerImpl() { }
    @Override
    public void runSimulator(SimulationDocument simulationDocument) {

        this.simulationExecutorService.execute(new SimulationRunnerImpl(simulationDocument));
        //this.simulationExecutorService.
    }

    @Override
    public void initThreadPoolService(Integer threadCount) {
        this.simulationExecutorService = Executors.newFixedThreadPool(threadCount);
    }

    @Override
    public void stopSimulation(SimulationDocument simulationDocument) {
        simulationDocument.setSimulationStatus(SimulationStatus.STOPPED);
    }

    @Override
    public void pauseSimulation(SimulationDocument simulationDocument) {
        simulationDocument.setSimulationStatus(SimulationStatus.PAUSED);
    }

    @Override
    public void resumeSimulation(SimulationDocument simulationDocument) {
        simulationDocument.setSimulationStatus(SimulationStatus.RUNNING);
    }


}
