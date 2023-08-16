package simulator.manager.api;
import dto.BuildSimulatorDto;
import dto.EnvironmentPropertiesDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;
import simulator.definition.property.enums.ePropertyType;

public interface SimulatorManager {

    // We want to return informative response: Id, simulationID, status of building, etc.
    // Strategy design pattern? readFile vs. UI input
    // Option A: 1. ReadFile 2. Deserialize 3. Fill World Obj
    // Option B: GetDto , then fill Obj
    public SimulatorResponse buildSimulationWorld(String filePath);


    // Parameters: consider support SimulationId and SimulationName
    // According to the simulation state (executed? or just built), we should return different types of data.
    public Object getSimulationWorldDetails();

    public EnvironmentPropertiesDto getEnvironmentProperties();

    public SimulatorResponse setEnvironmentVariableValue(String propName, String type, String value);

    public Object activateEnvironment();

    // Return type: PredictionsResponse<T> , PredictionsResponse<SimulationResultDTO>
    // T data , String status, string message
    public SimulatorResponse runSimulator();

    // Not sure what we should return here, maybe just a clean-up procedure.
    public Object exitSimulator();


    public void endSetEnvironmentSession();

    SimulatorResponse startEnvironmentSession();
    SimulatorResponse endEnvironmentSession();
    SimulatorResponse endLoadingSimulationSessionSignal();
    SimulatorResponse startLoadingSimulationSessionSignal();
}
