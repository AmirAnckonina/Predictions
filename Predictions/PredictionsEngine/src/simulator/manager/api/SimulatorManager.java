package simulator.manager.api;
import dto.EnvironmentPropertiesDto;
import dto.EstablishedEnvironmentInfoDto;
import dto.SimulationDetailsDto;
import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.result.manager.api.SimulatorResultManager;

public interface SimulatorManager {

    public SimulatorResponse buildSimulationWorld(String filePath);
    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails();
    public  SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition();
    public SimulatorResponse setSelectedEnvironmentVariablesValue(String propName, String type, String value);
    public SimulatorResponse establishSimulation();
    public SimulatorResponse<SimulationEndDto> runSimulator();
    public SimulatorResponse exitSimulator();
    SimulatorResultManager getSimulatorResultManagerImpl();
    SimulatorResponse startEnvironmentSession();
    SimulatorResponse endEnvironmentSession();
    SimulatorResponse endLoadingSimulationSessionSignal();
    SimulatorResponse startLoadingSimulationSessionSignal();

    SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo();
}
