package simulator.store.api;

import dto.PredictionsEngineResponse;
import dto.orderRequest.NewSimulationRequestDto;
import dto.orderRequest.SimulationOrderRequestDetailsDto;
import enums.SimulationRequestStatus;

import java.util.List;

public interface StoreManager {

    PredictionsEngineResponse addNewSimulationRequest(NewSimulationRequestDto newSimulationRequestDto);
    PredictionsEngineResponse setSimulationRequestStatusByGuid(String requestGuid, SimulationRequestStatus status);
    List<SimulationOrderRequestDetailsDto> getAllCurrentRequestsList();
}
