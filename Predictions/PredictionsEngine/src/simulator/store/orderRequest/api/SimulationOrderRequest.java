package simulator.store.orderRequest.api;

import enums.SimulationRequestStatus;
import enums.TerminationType;

public interface SimulationOrderRequest {

    String getCustomerUsername();

    String getRequestGuid();

    String getSimulationWorldName();

    Integer getNumOfExecutions();

    TerminationType getTerminationType();

    SimulationRequestStatus getSimulationRequestStatus();

    void setSimulationRequestStatus(SimulationRequestStatus status);

    Integer getRunning();
    Integer getDone();
}
