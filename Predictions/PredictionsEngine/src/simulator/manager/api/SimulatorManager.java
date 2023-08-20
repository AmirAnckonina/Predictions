package simulator.manager.api;
import dto.EnvironmentPropertiesDto;
import dto.EstablishedEnvironmentInfoDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;

public interface SimulatorManager {

    public SimulatorResponse buildSimulationWorld(String filePath);
    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails();
    public EnvironmentPropertiesDto getEnvironmentPropertiesDefinition();
    public SimulatorResponse setSelectedEnvironmentVariablesValue(String propName, String type, String value);
    public SimulatorResponse establishSimulation();
    public SimulatorResponse<String> runSimulator();
    public SimulatorResponse exitSimulator();
    SimulatorResultManager getSimulatorResultManagerImpl();
    SimulatorResponse startEnvironmentSession();
    SimulatorResponse endEnvironmentSession();
    SimulatorResponse endLoadingSimulationSessionSignal();
    SimulatorResponse startLoadingSimulationSessionSignal();

    SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo();
}
