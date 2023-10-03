package simulator.store.orderRequest.api;

import enums.SimulationRequestStatus;
import enums.TerminationType;

public interface SimulationOrderRequest {

    String getCustomerUsername();

    String getSimulationWorldName();

    Integer getRequestedNumOfExecutions();

    Integer getNumOfExecutionsLeft();

    TerminationType getTerminationType();

    SimulationRequestStatus getSimulationRequestStatus();

    void setSimulationRequestStatus(SimulationRequestStatus status);
}
