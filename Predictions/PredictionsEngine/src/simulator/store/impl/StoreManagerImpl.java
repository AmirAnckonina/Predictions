package simulator.store.impl;

import dto.PredictionsEngineResponse;
import dto.orderRequest.NewSimulationRequestDto;
import enums.SimulationRequestStatus;
import enums.TerminationType;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.mainManager.utils.SimulatorUtils;
import simulator.store.api.StoreManager;
import simulator.store.orderRequest.api.SimulationOrderRequest;
import simulator.store.orderRequest.impl.SimulationOrderRequestImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreManagerImpl implements StoreManager {
    Map<String, SimulationOrderRequest> simulationOrderRequestMap;

    public StoreManagerImpl() {
        this.simulationOrderRequestMap = new HashMap<>();
    }

    @Override
    public PredictionsEngineResponse addNewSimulationRequest(NewSimulationRequestDto newSimulationRequestDto) {

        String requestGuid = SimulatorUtils.getGUID();
        String username = newSimulationRequestDto.getCustomerUsername();
        String simulationWorldName = newSimulationRequestDto.getSimulationWorldName();
        Integer requestedNumOfExecutions = newSimulationRequestDto.getNumOfExecution();
        Integer numOfExecutionsLeft = 0;
        TerminationType terminationType = newSimulationRequestDto.getTerminationType();
        SimulationRequestStatus simulationRequestStatus = SimulationRequestStatus.PENDING;
        List<SimulationDocument> simulationDocumentList = new ArrayList<>();

        SimulationOrderRequest simulationOrderRequest =
                new SimulationOrderRequestImpl(requestGuid, username, simulationWorldName, requestedNumOfExecutions, numOfExecutionsLeft, terminationType, simulationRequestStatus, simulationDocumentList);

        this.simulationOrderRequestMap.put(requestGuid, simulationOrderRequest);

        return new PredictionsEngineResponse(true, "Simulation request added successfully in our system");
    }

    @Override
    public PredictionsEngineResponse setSimulationRequestStatusByGuid(String requestGuid, SimulationRequestStatus status) {
        this.simulationOrderRequestMap.get(requestGuid).setSimulationRequestStatus(status);
        return new PredictionsEngineResponse (true, "Simulation request status updated successfully");
    }
}
