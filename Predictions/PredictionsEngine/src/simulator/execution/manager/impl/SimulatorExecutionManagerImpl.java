package simulator.execution.manager.impl;

import dto.SimulationEndDto;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.manager.api.SimulatorExecutionManager;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.runner.api.SimulatorRunner;
import simulator.runner.impl.SimulatorRunnerImpl;

public class SimulatorExecutionManagerImpl implements SimulatorExecutionManager {
    private WorldInstance worldInstance;
    private EstablishmentManager establishmentManager;
    private SimulatorRunner simulatorRunner;

    public SimulatorExecutionManagerImpl() {
    }

    @Override
    public SimulationEndDto runSimulator(SimulationDocument simulationDocument) {

        this.simulatorRunner = new SimulatorRunnerImpl(simulationDocument);
        this.simulatorRunner.run();

        //need to add DTO to return termination reason.
        return new SimulationEndDto(
                        simulationDocument.getSimulationGuid(),
                        simulationDocument.getSimulationResult().getTerminationReason().toString()
        );
    }

    @Override
    public WorldInstance getWorldInstance() {
        return this.worldInstance;
    }
}
