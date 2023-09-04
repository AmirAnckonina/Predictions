package simulator.execution.manager.impl;

import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.manager.api.ExecutionManager;
import simulator.result.api.SimulationResult;
import simulator.runner.api.SimulatorRunner;
import simulator.runner.impl.SimulatorRunnerImpl;

public class ExecutionManagerImpl implements ExecutionManager {
    private WorldInstance worldInstance;
    private EstablishmentManager establishmentManager;
    SimulatorRunner simulatorRunner;

    public ExecutionManagerImpl() {
    }

    @Override
    public SimulatorResponse<SimulationEndDto> runSimulator(SimulationResult simulationResult) {

        try {
            this.simulatorRunner = new SimulatorRunnerImpl(this.worldInstance);
            this.simulatorRunner.run(simulationResult);

            //need to add DTO to return termination reason.
            return new SimulatorResponse<SimulationEndDto>(
                    true,
                    "run succesffuly, ID : " + simulationResult.getSimulationUuid(),
                    new SimulationEndDto(
                            simulationResult.getSimulationUuid(),
                            simulationResult.getTerminationReason().toString()
                    )
            );

        } catch (Exception e) {

            return new SimulatorResponse(
                    false,
                    "An error detected while running the simulation, couldn't complete the simulation"
            );
        }
    }

    @Override
    public WorldInstance getWorldInstance() {
        return this.worldInstance;
    }
}
