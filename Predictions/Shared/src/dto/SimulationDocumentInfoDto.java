package dto;

import enums.SimulationStatus;

import java.util.Map;

public class SimulationDocumentInfoDto {
    private String simulationGuid;
    private SimulationStatus simulationStatus;
    private Integer tickNo;
    private Long timePassedInSeconds;
    private Map<String, Integer> currentEntityPopulationMap;
    private Map<String, Integer> initialEntityPopulationMap;

    public SimulationDocumentInfoDto(String simulationGuid, SimulationStatus simulationStatus, Integer tickNo, Long timePassedInSeconds, Map<String, Integer> currentEntityPopulationMap, Map<String, Integer> initialEntityPopulationMap) {
        this.simulationGuid = simulationGuid;
        this.simulationStatus = simulationStatus;
        this.tickNo = tickNo;
        this.timePassedInSeconds = timePassedInSeconds;
        this.currentEntityPopulationMap = currentEntityPopulationMap;
        this.initialEntityPopulationMap = initialEntityPopulationMap;
    }

    public String getSimulationGuid() {
        return simulationGuid;
    }

    public SimulationStatus getSimulationStatus() {
        return simulationStatus;
    }

    public Integer getTickNo() {
        return tickNo;
    }

    public Long getTimePassedInSeconds() {
        return timePassedInSeconds;
    }

    public Map<String, Integer> getCurrentEntityPopulationMap() {
        return currentEntityPopulationMap;
    }

    public Map<String, Integer> getInitialEntityPopulationMap() {
        return initialEntityPopulationMap;
    }
}
