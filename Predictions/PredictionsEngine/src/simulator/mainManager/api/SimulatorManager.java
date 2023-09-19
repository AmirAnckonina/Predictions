package simulator.mainManager.api;
import dto.*;
import simulator.information.simulationDocument.api.SimulationDocumentFacade;
import simulator.result.manager.api.ResultManager;

import java.util.List;

public interface SimulatorManager {

    void buildSimulationWorld(String filePath);
    SimulationDetailsDto getSimulationWorldDetails();
    EnvironmentPropertiesDto getEnvironmentPropertiesDefinition();
    void setSelectedEnvironmentPropertiesValue(String propName, String type, String value);
    void setEntityDefinitionPopulation(String entityName, Integer entityPopulation);
    SimulationDocumentInfoDto runSimulator();
    void exitSimulator();
    ResultManager getSimulatorResultManagerImpl();
    EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo();
    List<String> getAllEntities();
    <T> void setEnvironmentPropertyValue(String envPropertyName, T envPropertyValue);
    List<EnvironmentPropertyDto> getAllEnvironmentProperties();
    void resetSingleEntityPopulation(String entityName);
    void resetSingleEnvironmentVariable(String envVarName);
    Integer getMaxPopulationSize();
    void resetAllManualSetup();
    void stopSimulationByGuid(String GUID);
    void pauseSimulationByGuid(String GUID);
    void resumeSimulationByGuid(String GUID);
    SimulationDocumentInfoDto getSimulationDocumentInfo(String guid);
}
