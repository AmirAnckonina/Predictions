package simulator.execution.runner.api;

public interface SimulatorRunner {
    void createInstances();
    void activateEnvironment();
    void reset();

    void run();
    //SimulationResult run();
}
