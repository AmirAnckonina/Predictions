package simulator.store.api;

import dto.PredictionsEngineResponse;
import dto.orderRequest.NewSimulationRequestDto;
import enums.SimulationRequestStatus;

public interface StoreManager {

    PredictionsEngineResponse addNewSimulationRequest(NewSimulationRequestDto newSimulationRequestDto);
    PredictionsEngineResponse setSimulationRequestStatusByGuid(String requestGuid, SimulationRequestStatus status);


}
