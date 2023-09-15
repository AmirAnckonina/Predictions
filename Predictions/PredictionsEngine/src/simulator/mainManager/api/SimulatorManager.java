package simulator.mainManager.api;
import dto.*;
import response.SimulatorResponse;
import simulator.definition.world.WorldDefinition;
import simulator.result.manager.api.ResultManager;

import java.util.List;

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
    List<String> getAllEntities();
    <T> void setEnvironmentPropertyValue(String envPropertyName, T envPropertyValue);
    List<EnvironmentPropertyDto> getAllEnvironmentProperties();
    void resetSingleEntityPopulation(String entityName);
    void resetSingleEnvironmentVariable(String envVarName);
}
