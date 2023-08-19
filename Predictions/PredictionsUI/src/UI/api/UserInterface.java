package UI.api;

import dto.SimulationDetailsDto;

public interface UserInterface {
    void runSimulatorUI();
    void loadSimulationSession();
    void showLoadedSimulationWorldDetails();
    void runSimulationSession();
    void showHistoricalSimulationResult();
    void exitSimulator();
}
