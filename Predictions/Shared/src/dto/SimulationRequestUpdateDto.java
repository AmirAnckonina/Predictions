package dto;

import enums.SimulationRequestStatus;

public class SimulationRequestUpdateDto {
    private final String requestGuid;
    private final SimulationRequestStatus simulationRequestStatus;

    public SimulationRequestUpdateDto(String requestGuid, SimulationRequestStatus simulationRequestStatus) {
        this.requestGuid = requestGuid;
        this.simulationRequestStatus = simulationRequestStatus;
    }

    public String getRequestGuid() {
        return requestGuid;
    }

    public SimulationRequestStatus getSimulationRequestStatus() {
        return simulationRequestStatus;
    }
}
