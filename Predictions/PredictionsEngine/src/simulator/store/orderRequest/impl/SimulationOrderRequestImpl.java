package simulator.store.orderRequest.impl;

import enums.SimulationRequestStatus;
import enums.TerminationType;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.store.orderRequest.api.SimulationOrderRequest;

import java.util.List;

public class SimulationOrderRequestImpl implements SimulationOrderRequest {
    private String requestGuid;
    private String customerUsername;
    private String simulationWorldName;
    private Integer requestedNumOfExecutions;
    private Integer numOfExecutionsLeft;
    private TerminationType terminationType;
    private SimulationRequestStatus simulationRequestStatus;
    private List<SimulationDocument> simulationDocumentMap;

    public SimulationOrderRequestImpl(String requestGuid, String customerUsername, String simulationWorldName, Integer requestedNumOfExecutions, Integer numOfExecutionsLeft, TerminationType terminationType, SimulationRequestStatus simulationRequestStatus, List<SimulationDocument> simulationDocumentMap) {
        this.requestGuid = requestGuid;
        this.customerUsername = customerUsername;
        this.simulationWorldName = simulationWorldName;
        this.requestedNumOfExecutions = requestedNumOfExecutions;
        this.numOfExecutionsLeft = numOfExecutionsLeft;
        this.terminationType = terminationType;
        this.simulationRequestStatus = simulationRequestStatus;
        this.simulationDocumentMap = simulationDocumentMap;
    }

    @Override
    public String getCustomerUsername() {
        return customerUsername;
    }

    @Override
    public String getRequestGuid() {
        return requestGuid;
    }

    @Override
    public String getSimulationWorldName() {
        return simulationWorldName;
    }

    @Override
    public Integer getRequestedNumOfExecutions() {
        return requestedNumOfExecutions;
    }

    @Override
    public Integer getNumOfExecutionsLeft() {
        return numOfExecutionsLeft;
    }

    @Override
    public TerminationType getTerminationType() {
        return terminationType;
    }

    @Override
    public SimulationRequestStatus getSimulationRequestStatus() {
        return simulationRequestStatus;
    }

    @Override
    public void setSimulationRequestStatus(SimulationRequestStatus status) {
        this.simulationRequestStatus = status;
    }
}
