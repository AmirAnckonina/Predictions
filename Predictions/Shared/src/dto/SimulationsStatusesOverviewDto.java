package dto;

import enums.OverviewSimulationStatus;
import enums.SimulationStatus;

import java.util.Map;

public class SimulationsStatusesOverviewDto {
    private Map<OverviewSimulationStatus, Integer> simulationsStatusesOverviewMap;

    public SimulationsStatusesOverviewDto(Map<OverviewSimulationStatus, Integer> simulationsStatusesOverviewMap) {
        this.simulationsStatusesOverviewMap = simulationsStatusesOverviewMap;
    }

    public Map<OverviewSimulationStatus, Integer> getSimulationsStatusesOverviewMap() {
        return simulationsStatusesOverviewMap;
    }
}
