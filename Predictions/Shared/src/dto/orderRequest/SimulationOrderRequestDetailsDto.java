package dto.orderRequest;

import enums.SimulationRequestStatus;
import enums.TerminationType;

public class SimulationOrderRequestDetailsDto {
    private final String requestGuid;
    private final String simulationWorldName;
    private final Integer numOfExecutions;
    private final TerminationType terminationType;
    private final SimulationRequestStatus simulationRequestStatus;
    private final Integer running;
    private final Integer done;

    public SimulationOrderRequestDetailsDto(String requestGuid, String simulationWorldName, Integer numOfExecutions, TerminationType terminationType, SimulationRequestStatus simulationRequestStatus, Integer running, Integer done) {
        this.requestGuid = requestGuid;
        this.simulationWorldName = simulationWorldName;
        this.numOfExecutions = numOfExecutions;
        this.terminationType = terminationType;
        this.simulationRequestStatus = simulationRequestStatus;
        this.running = running;
        this.done = done;
    }

    public Integer getNumOfExecutions() {
        return numOfExecutions;
    }

    public String getRequestGuid() {
        return requestGuid;
    }

    public String getSimulationWorldName() {
        return simulationWorldName;
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
