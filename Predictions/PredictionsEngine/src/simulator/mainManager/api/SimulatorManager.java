package simulator.mainManager.api;
import dto.EnvironmentPropertiesDto;
import dto.EstablishedEnvironmentInfoDto;
import dto.SimulationDetailsDto;
import dto.SimulationEndDto;
import response.SimulatorResponse;
import simulator.definition.world.WorldDefinition;
import simulator.result.manager.api.ResultManager;

public interface SimulatorManager {

    void buildSimulationWorld(String filePath);
    SimulationDetailsDto getSimulationWorldDetails();
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition();
    void setSelectedEnvironmentPropertiesValue(String propName, String type, String value);
    void setEntityDefinitionPopulation(String entityName, Integer entityPopulation);
    void establishSimulation();
    SimulationEndDto runSimulator();
    void exitSimulator();
    ResultManager getSimulatorResultManagerImpl();
    EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo();
}
