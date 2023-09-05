package simulator.mainManager.api;
import dto.EnvironmentPropertiesDto;
import dto.EstablishedEnvironmentInfoDto;
import dto.SimulationDetailsDto;
import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.definition.world.WorldDefinition;
import simulator.result.manager.api.ResultManager;

public interface SimulatorManager {

    SimulatorResponse buildSimulationWorld(String filePath);
    SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails();
    SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition();
    SimulatorResponse setSelectedEnvironmentPropertiesValue(String propName, String type, String value);
    SimulatorResponse setEntityDefinitionPopulation(WorldDefinition worldDefinition, String entityName, Integer entityPopulation);
    SimulatorResponse establishSimulation();
    SimulatorResponse<SimulationEndDto> runSimulator();
    SimulatorResponse exitSimulator();
    ResultManager getSimulatorResultManagerImpl();
    SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo();
}