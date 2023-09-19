package dto;

import enums.SimulationStatus;

public class SimulationDocumentInfoDto {
    private String simulationGuid;
    private SimulationStatus simulationStatus;
    private int tickNo;
    private long timePassedInSeconds;

    public SimulationDocumentInfoDto(String simulationGuid, SimulationStatus simulationStatus, int tickNo, long timePassedInSeconds) {
        this.simulationGuid = simulationGuid;
        this.simulationStatus = simulationStatus;
        this.tickNo = tickNo;
        this.timePassedInSeconds = timePassedInSeconds;
    }

    public String getSimulationGuid() {
        return simulationGuid;
    }

    public int getTickNo() {
        return tickNo;
    }

    public long getTimePassedInSeconds() {
        return timePassedInSeconds;
    }

    public SimulationStatus getSimulationStatus() {
        return simulationStatus;
    }
}
