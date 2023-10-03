package dto.orderRequest;

import enums.SimulationRequestStatus;
import enums.TerminationType;

public class SimulationOrderRequestDetailsDto {
    private final String requestGuid;
    private final String simulationWorldName;
    private final Integer numOfExecutionLeft;
    private final TerminationType terminationType;
    private final SimulationRequestStatus simulationRequestStatus;
    private final Integer running;
    private final Integer done;

    public SimulationOrderRequestDetailsDto(String requestGuid, String simulationWorldName, Integer numOfExecutionLeft, TerminationType terminationType, SimulationRequestStatus simulationRequestStatus, Integer running, Integer done) {
        this.requestGuid = requestGuid;
        this.simulationWorldName = simulationWorldName;
        this.numOfExecutionLeft = numOfExecutionLeft;
        this.terminationType = terminationType;
        this.simulationRequestStatus = simulationRequestStatus;
        this.running = running;
        this.done = done;
    }

    public String getRequestGuid() {
        return requestGuid;
    }

    public String getSimulationWorldName() {
        return simulationWorldName;
    }

    public Integer getNumOfExecutionLeft() {
        return numOfExecutionLeft;
    }

    public SimulationRequestStatus getSimulationRequestStatus() {
        return simulationRequestStatus;
    }

    public Integer getRunning() {
        return running;
    }

    public Integer getDone() {
        return done;
    }

    public TerminationType getTerminationType() {
        return terminationType;
    }
}
