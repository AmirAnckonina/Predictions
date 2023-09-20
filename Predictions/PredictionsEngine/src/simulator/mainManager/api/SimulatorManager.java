package simulator.mainManager.api;
import dto.*;

import java.util.List;

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
    List<String> getPropertiesByEntity(String entityName);
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
    SimulationsStatusesOverviewDto collectAllSimulationsStatuses();
    SimulationResultMappedProperties getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String entityName, String guid);

}
