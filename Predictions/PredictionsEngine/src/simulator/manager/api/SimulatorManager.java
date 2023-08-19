package simulator.manager.api;
import dto.EnvironmentPropertiesDto;
import dto.EstablishedEnvironmentInfoDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;
import simulator.manager.impl.SimulatorResultManagerImpl;

public interface SimulatorManager {

    public SimulatorResponse buildSimulationWorld(String filePath);

    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails();

    public EnvironmentPropertiesDto getEnvironmentProperties();

    public SimulatorResponse setEnvironmentVariableValue(String propName, String type, String value);

    public SimulatorResponse establishSimulation();
    public SimulatorResponse runSimulator();

    public SimulatorResponse exitSimulator();

    SimulatorResultManager getSimulatorResultManagerImpl();

    public void endSetEnvironmentSession();

    SimulatorResponse startEnvironmentSession();
    SimulatorResponse endEnvironmentSession();
    SimulatorResponse endLoadingSimulationSessionSignal();
    SimulatorResponse startLoadingSimulationSessionSignal();

    SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo();
}
