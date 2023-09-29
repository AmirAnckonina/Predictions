package simulator.mainManager.impl;

import dto.*;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.builder.manager.impl.WorldBuilderManagerImpl;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.mainManager.utils.exception.SimulatorManagerException;
import simulator.manualSetup.manager.api.ManualSimulationSetupManager;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.manualSetup.manager.impl.ManualSimulationSetupManagerImpl;
import simulator.establishment.manager.impl.EstablishmentManagerImpl;
import simulator.execution.manager.api.ExecutionManager;
import simulator.execution.manager.impl.ExecutionManagerImpl;
import simulator.information.manager.api.InformationManager;
import simulator.information.manager.impl.InformationManagerImpl;
import simulator.mainManager.api.SimulatorManager;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatorManagerImpl implements SimulatorManager {

    private EstablishmentManager establishmentManager;
    private ManualSimulationSetupManager manualSimulationSetupManager;
    private WorldBuilderManager worldBuilderManager;
    private ExecutionManager executionManager;
    private InformationManager infoManager;

    public SimulatorManagerImpl() {
        this.worldBuilderManager = new WorldBuilderManagerImpl();
        this.executionManager = new ExecutionManagerImpl();
        this.infoManager = new InformationManagerImpl();
        this.establishmentManager = new EstablishmentManagerImpl();
        this.manualSimulationSetupManager = new ManualSimulationSetupManagerImpl();
    }

    public SimulatorManagerImpl(EstablishmentManager establishmentManager, ManualSimulationSetupManager manualSimulationSetupManager, WorldBuilderManager worldBuilderManager, ExecutionManager executionManager, InformationManager infoManager) {
        this.establishmentManager = establishmentManager;
        this.manualSimulationSetupManager = manualSimulationSetupManager;
        this.worldBuilderManager = worldBuilderManager;
        this.executionManager = executionManager;
        this.infoManager = infoManager;
    }

    @Override
    public void buildSimulationWorld(String filePath) {
        throw new SimulatorManagerException("Impl of buildSimulationWorld method is now directly from worldBuilderManager");
        //this.worldBuilderManager.buildSimulationWorld(filePath);

    }

    @Override
    public SimulationWorldDetailsDto getSimulationWorldDetails() {
        throw new SimulatorManagerException("Impl of getSimulationWorldDetails method is now directly from worldBuilderManager");
        //return worldBuilderManager.getSimulationWorldDetailsByName(simulationWorldName);
    }

    @Override
    public EnvironmentPropertiesDto getEnvironmentPropertiesDefinition(String simulationWorldName) {
        return worldBuilderManager.getEnvironmentPropertiesDefinition(simulationWorldName);
    }

    @Override
    public void setSelectedEnvironmentPropertiesValue(String propName, String type, String value, String simulationWorldName) {
        manualSimulationSetupManager
                .setSelectedEnvironmentPropertiesValue(
                        worldBuilderManager.getWorldDefinition(simulationWorldName),
                        propName,
                        type,
                        value
                );
    }

    @Override
    public SimulationDocumentInfoDto runSimulator(String simulationWorldName) {

        establishmentManager.establishSimulation(this.worldBuilderManager.getWorldDefinition(simulationWorldName));
        SimulationDocument simulationDocument =
                infoManager.createNewSimulationDocument(
                        worldBuilderManager.getWorldDefinition(simulationWorldName), establishmentManager.getEstablishedWorldInstance());

        executionManager.runSimulator(simulationDocument);
        return this.infoManager.getInitialSimulationDocumentInfoDto(simulationDocument.getSimulationGuid());
    }

    @Override
    public void exitSimulator() {
        throw new SimulatorRunnerException("Not Impl Exit Simulator method");
    }

    @Override
    public EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo() {
        return establishmentManager.getEstablishedEnvironmentInfo();
    }

    @Override
    public List<String> getAllEntities(String simulationWorldName) {
        return new ArrayList<>(this.worldBuilderManager
                .getWorldDefinition(simulationWorldName)
                .getEntities()
                .keySet());
    }

    @Override
    public List<String> getAllProperties(String simulationWorldName) {
        Map<String, String> propertiesMap = new HashMap<>();
        List<String> propertiesList = new ArrayList<>();

        this.worldBuilderManager
                .getWorldDefinition(simulationWorldName)
                .getEntities()
                .keySet()
                .forEach(entity -> insertEntityPropertiesToMapByEntityName(simulationWorldName, propertiesMap, entity));

        propertiesList.addAll(propertiesMap.keySet());
        return propertiesList;
    }

    @Override
    public List<String> getPropertiesByEntity(String simulationWorldName, String entityName) {
        List<String> properties = new ArrayList<>();
        properties.addAll(this.worldBuilderManager.getWorldDefinition(simulationWorldName).getEntities().get(entityName).getProperties().keySet());

        return properties;
    }

    private void insertEntityPropertiesToMapByEntityName(String simulationWorldName, Map<String, String> propertiesMap, String entity) {
        this.worldBuilderManager
                .getWorldDefinition(simulationWorldName)
                .getEntities()
                .get(entity)
                .getProperties()
                .keySet()
                .forEach(property -> propertiesMap.put(property, property));
    }

    @Override
    public void setEntityDefinitionPopulation(String simulationWorldName, String entityName, Integer entityPopulation) {
        manualSimulationSetupManager
                .setEntityDefinitionPopulation(
                        worldBuilderManager.getWorldDefinition(simulationWorldName), entityName, entityPopulation);

    }

    @Override
    public <T> void setEnvironmentPropertyValue(String simulationWorldName, String envPropertyName, T envPropertyValue) {
        this.manualSimulationSetupManager.setEnvironmentPropertyValue(
                this.worldBuilderManager.getWorldDefinition(simulationWorldName), envPropertyName, envPropertyValue);
    }

    @Override
    public List<EnvironmentPropertyDto> getAllEnvironmentProperties(String simulationWorldName) {
        return this.worldBuilderManager.getAllEnvironmentProperties(simulationWorldName);
    }

    @Override
    public void resetSingleEntityPopulation(String simulationWorldName, String entityName) {
        this.manualSimulationSetupManager
                .resetSingleEntityPopulation(this.worldBuilderManager.getWorldDefinition(simulationWorldName), entityName);
    }

    @Override
    public void resetSingleEnvironmentVariable(String simulationWorldName, String envVarName) {
        this.manualSimulationSetupManager
                .resetSingleEnvironmentVariable(this.worldBuilderManager.getWorldDefinition(simulationWorldName), envVarName);
    }

    @Override
    public Integer getMaxPopulationSize(String simulationWorldName) {
        return this.worldBuilderManager.getMaxPopulationSize(simulationWorldName);
    }

    @Override
    public void resetAllManualSetup(String simulationWorldName) {
        this.manualSimulationSetupManager.resetAllManualSetup(this.worldBuilderManager.getWorldDefinition(simulationWorldName));
    }

    @Override
    public SimulationDocumentInfoDto stopSimulationByGuid(String guid) {
        this.executionManager.stopSimulation(this.infoManager.getSimulationDocumentByGuid(guid));
        return this.infoManager.getLatestSimulationDocumentInfoDto(guid);
    }

    @Override
    public SimulationDocumentInfoDto pauseSimulationByGuid(String guid) {
        this.executionManager.pauseSimulation(this.infoManager.getSimulationDocumentByGuid(guid));
        return this.infoManager.getLatestSimulationDocumentInfoDto(guid);
    }

    @Override
    public SimulationDocumentInfoDto resumeSimulationByGuid(String guid) {
        this.executionManager.resumeSimulation(this.infoManager.getSimulationDocumentByGuid(guid));
        return this.infoManager.getLatestSimulationDocumentInfoDto(guid);
    }

    @Override
    public SimulationDocumentInfoDto getLatestSimulationDocumentInfo(String guid) {
         return this.infoManager.getLatestSimulationDocumentInfoDto(guid);
    }

    @Override
    public SimulationResultMappedProperties getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName, String entityName, String guid) {
        SimulationResultMappedProperties mappedProperties = new SimulationResultMappedProperties(
                infoManager.getMappedPropertiesToNumOfEntitiesWithSameValues(propertyName, entityName, guid),
                guid);
        return mappedProperties;
    }

    @Override
    public SimulationManualParamsDto getSimulationManualParamsByGuid(String simulationGuid) {
        return this.infoManager.getSimulationManualParamsByGuid(simulationGuid);
    }

    @Override
    public SimulationsStatusesOverviewDto collectAllSimulationsStatuses() {
        return this.infoManager.collectAllSimulationsStatusesDto();
    }


    @Override
    public EntityPopulationOvertimeDto getEntityPopulationOvertimeByGuid(String guid) {
        return this.infoManager.getSimulationDocumentByGuid(guid).getSimulationResult().getEntitiesPopulationOvertimeMap();
    }

    @Override
    public PropertiesConsistencyDto getEntitiesPropertiesConsistencyMapByGuid(String guid) {
        return this.infoManager.getSimulationDocumentByGuid(guid).getSimulationResult().getEntitiesPropertiesConsistencyMap();
    }

    @Override
    public PropertiesAvgConsistencyDto getEntitiesNumericPropertiesAverageByGuid(String guid) {
        return this.infoManager.getSimulationDocumentByGuid(guid).getSimulationResult().getEntitiesNumericPropertiesAvg();
    }
}
