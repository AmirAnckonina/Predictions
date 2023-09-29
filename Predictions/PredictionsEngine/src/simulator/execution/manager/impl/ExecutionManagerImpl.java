package simulator.execution.manager.impl;

import enums.SimulationExecutionStatus;
import simulator.execution.manager.api.ExecutionManager;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.runner.impl.SimulationRunnerImpl;

import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager {
    private ThreadPoolExecutor simulationExecutorService;

    public ExecutionManagerImpl() { }
    @Override
    public void runSimulator(SimulationDocument simulationDocument) {
        this.simulationExecutorService.execute(new SimulationRunnerImpl(simulationDocument));
    }

    @Override
    public void initThreadPoolService(Integer threadCount) {
        this.simulationExecutorService = new ThreadPoolExecutor(threadCount, threadCount, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        //this.simulationExecutorService = Executors.newFixedThreadPool(threadCount);
    }

    @Override
    public void stopSimulation(SimulationDocument simulationDocument) {
        simulationDocument.setSimulationStatus(SimulationExecutionStatus.STOPPED);
    }

    @Override
    public void pauseSimulation(SimulationDocument simulationDocument) {
        simulationDocument.setSimulationStatus(SimulationExecutionStatus.PAUSED);
    }

    @Override
    public void resumeSimulation(SimulationDocument simulationDocument) {
        simulationDocument.setSimulationStatus(SimulationExecutionStatus.RUNNING);
    }


}
