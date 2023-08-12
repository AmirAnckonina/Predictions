package simulator.execution.api;

public interface SimulatorRunner {
    void createInstances();
    void activateEnvironment();
    void reset();
    void run();
}
