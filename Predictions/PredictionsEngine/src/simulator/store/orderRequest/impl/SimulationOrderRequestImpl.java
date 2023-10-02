package simulator.store.orderRequest.impl;

import enums.SimulationRequestStatus;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.store.orderRequest.api.SimulationOrderRequest;

import java.util.List;

public class SimulationOrderRequestImpl implements SimulationOrderRequest {
    private String requestGuid;
    private String customerUsername;
    private String simulationWorldName;
    private Integer requestedNumOfExecutions;
    private Integer numOfExecutionsLeft;
    SimulationRequestStatus simulationRequestStatus;
    List<SimulationDocument> simulationDocumentMap;
}
