package simulator.mainManager.api;
import dto.*;

import java.util.List;
import java.util.Map;

public interface SimulatorManager {

    void buildSimulationWorld(String filePath);
    SimulationDetailsDto getSimulationWorldDetails();
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition();
    void setSelectedEnvironmentPropertiesValue(String propName, String type, String value);
    void setEntityDefinitionPopulation(String entityName, Integer entityPopulation);
    SimulationDocumentInfoDto runSimulator();
    void exitSimulator();
    EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo();
    List<String> getAllEntities();
    List<String> getAllProperties();
    <T> void setEnvironmentPropertyValue(String envPropertyName, T envPropertyValue);
    List<EnvironmentPropertyDto> getAllEnvironmentProperties();
    void resetSingleEntityPopulation(String entityName);
    void resetSingleEnvironmentVariable(String envVarName);
    Integer getMaxPopulationSize();
    void resetAllManualSetup();
    SimulationDocumentInfoDto stopSimulationByGuid(String guid);
    SimulationDocumentInfoDto pauseSimulationByGuid(String guid);
    SimulationDocumentInfoDto resumeSimulationByGuid(String guid);
    SimulationDocumentInfoDto getLatestSimulationDocumentInfo(String guid);

    SimulationResultMappedProperties getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String string);
}
