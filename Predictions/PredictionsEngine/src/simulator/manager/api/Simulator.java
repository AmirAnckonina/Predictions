package simulator.api;
import dto.BuildSimulatorDto;
import dto.LoadSimulationFileDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;

public interface Simulator {

    // We want to return informative response: Id, simulationID, status of building, etc.
    // Strategy design pattern? readFile vs. UI input
    // Option A: 1. ReadFile 2. Deserialize 3. Fill World Obj
    // Option B: GetDto , then fill Obj
    public BuildSimulatorDto buildSimulator(String filePath);


    // Parameters: consider support SimulationId and SimulationName
    // According to the simulation state (executed? or just built), we should return different types of data.
    public Object getSimulationDetails();

    // Return type: PredictionsResponse<T> , PredictionsResponse<SimulationResultDTO>
    // T data , String status, string message
    public Object runSimulator();

    // Not sure what we should return here, maybe just a clean-up procedure.
    public Object exitSimulator();
}
