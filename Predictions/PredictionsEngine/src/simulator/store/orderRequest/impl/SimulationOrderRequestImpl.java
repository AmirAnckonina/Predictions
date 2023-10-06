package simulator.store.orderRequest.impl;

import enums.SimulationExecutionStatus;
import enums.SimulationRequestStatus;
import enums.TerminationType;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.store.orderRequest.api.SimulationOrderRequest;

import java.util.List;

public class SimulationOrderRequestImpl implements SimulationOrderRequest {
    private String requestGuid;
    private String customerUsername;
    private String simulationWorldName;
    private Integer numOfExecutions;
    private TerminationType terminationType;
    private SimulationRequestStatus simulationRequestStatus;
    private List<SimulationDocument> simulationDocumentList;

    public SimulationOrderRequestImpl(String requestGuid, String customerUsername, String simulationWorldName, Integer numOfExecutions, TerminationType terminationType, SimulationRequestStatus simulationRequestStatus, List<SimulationDocument> simulationDocumentList) {
        this.requestGuid = requestGuid;
        this.customerUsername = customerUsername;
        this.simulationWorldName = simulationWorldName;
        this.numOfExecutions = numOfExecutions;
        this.terminationType = terminationType;
        this.simulationRequestStatus = simulationRequestStatus;
        this.simulationDocumentList = simulationDocumentList;
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
    public Integer getNumOfExecutions() {
        return numOfExecutions;
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

    @Override
    public Integer getRunning() {
        Long running =
                this.simulationDocumentList
                        .stream()
                        .filter(simulationDocument ->
                                simulationDocument.getSimulationStatus() == SimulationExecutionStatus.RUNNING)
                        .count();


        return running.intValue();
    }

    @Override
    public Integer getDone() {
        Long done =
                this.simulationDocumentList
                        .stream()
                        .filter(simulationDocument ->
                                simulationDocument.getSimulationStatus() == SimulationExecutionStatus.COMPLETED
                                || simulationDocument.getSimulationStatus() == SimulationExecutionStatus.STOPPED)
                        .count();

        return done.intValue();
    }
}
