package simulator.execution.runner.api;

import simulator.execution.runner.utils.exceptions.eReasonForTerminate;
import simulator.manager.api.SimulationResult;

public interface SimulatorRunner {
    void run(SimulationResult simulationResult);
}
