package dto.worldBuilder;

import java.util.List;

public class SimulationWorldNamesDto {
    private List<String> simulationWorldNames;

    public SimulationWorldNamesDto(List<String> simulationWorldNames) {
        this.simulationWorldNames = simulationWorldNames;
    }

    public List<String> getSimulationWorldNames() {
        return simulationWorldNames;
    }
}
