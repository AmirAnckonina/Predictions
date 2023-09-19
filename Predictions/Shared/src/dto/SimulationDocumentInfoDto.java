package dto;

import enums.SimulationStatus;

import java.util.Map;

public class SimulationDocumentInfoDto {
    private String simulationGuid;
    private SimulationStatus simulationStatus;
    private Integer tickNo;
    private Long timePassedInSeconds;
    private Map<String, Integer> entityPopulationMap;

    public SimulationDocumentInfoDto(String simulationGuid, SimulationStatus simulationStatus, int tickNo, long timePassedInSeconds, Map<String, Integer> entityPopulationMap) {
        this.simulationGuid = simulationGuid;
        this.simulationStatus = simulationStatus;
        this.tickNo = tickNo;
        this.timePassedInSeconds = timePassedInSeconds;
        this.entityPopulationMap = entityPopulationMap;
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

    public Map<String, Integer> getEntityPopulationMap() {
        return entityPopulationMap;
    }
}
