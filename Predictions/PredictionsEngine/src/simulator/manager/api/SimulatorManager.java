package simulator.manager.api;
import dto.EnvironmentPropertiesDto;
import dto.EstablishedEnvironmentInfoDto;
import dto.SimulationDetailsDto;
import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.result.manager.api.SimulatorResultManager;

public interface SimulatorManager {

    SimulatorResponse buildSimulationWorld(String filePath);
    SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails();
    SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition();
    SimulatorResponse setSelectedEnvironmentVariablesValue(String propName, String type, String value);
    SimulatorResponse establishSimulation();
    SimulatorResponse<SimulationEndDto> runSimulator();
    SimulatorResponse exitSimulator();
    SimulatorResultManager getSimulatorResultManagerImpl();
    SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo();
}
