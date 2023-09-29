package simulator.mainManager.api;
import dto.*;

import java.util.List;

public interface SimulatorManager {

    void buildSimulationWorld(String filePath);
    SimulationWorldDetailsDto getSimulationWorldDetails();
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition(String simulationWorldName);
    void setSelectedEnvironmentPropertiesValue(String propName, String type, String value, String simulationWorldName);
    void setEntityDefinitionPopulation(String simulationWorldName, String entityName, Integer entityPopulation);
    SimulationDocumentInfoDto runSimulator(String simulationWorldName);
    void exitSimulator();
    EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo();
    List<String> getAllEntities(String simulationWorldName);

    List<String> getAllProperties(String simulationWorldName);

    List<String> getPropertiesByEntity(String simulationWorldName, String entityName);
    <T> void setEnvironmentPropertyValue(String simulationWorldName, String envPropertyName, T envPropertyValue);
    List<EnvironmentPropertyDto> getAllEnvironmentProperties(String simulationWorldName);
    void resetSingleEntityPopulation(String simulationWorldName, String entityName);
    void resetSingleEnvironmentVariable(String simulationWorldName, String envVarName);
    Integer getMaxPopulationSize(String simulationWorldName);
    void resetAllManualSetup(String simulationWorldName);
    SimulationDocumentInfoDto stopSimulationByGuid(String guid);
    SimulationDocumentInfoDto pauseSimulationByGuid(String guid);
    SimulationDocumentInfoDto resumeSimulationByGuid(String guid);
    SimulationDocumentInfoDto getLatestSimulationDocumentInfo(String guid);
    SimulationsStatusesOverviewDto collectAllSimulationsStatuses();
    SimulationResultMappedProperties getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String entityName, String guid);

    SimulationManualParamsDto getSimulationManualParamsByGuid(String simulationGuid);

    EntityPopulationOvertimeDto getEntityPopulationOvertimeByGuid(String guid);

    PropertiesConsistencyDto getEntitiesPropertiesConsistencyMapByGuid(String guid);

    PropertiesAvgConsistencyDto getEntitiesNumericPropertiesAverageByGuid(String guid);
}
