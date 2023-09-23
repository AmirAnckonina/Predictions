package simulator.mainManager.impl;

import dto.*;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.builder.manager.impl.WorldBuilderManagerImpl;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.manualSetup.manager.api.ManualSimulationSetupManager;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.manualSetup.manager.impl.ManualSimulationSetupManagerImpl;
import simulator.establishment.manager.impl.EstablishmentManagerImpl;
import simulator.execution.manager.api.SimulatorExecutionManager;
import simulator.execution.manager.impl.SimulatorExecutionManagerImpl;
import simulator.information.manager.api.InformationManager;
import simulator.information.manager.impl.InformationManagerImpl;
import simulator.mainManager.api.SimulatorManager;
import simulator.result.manager.api.ResultManager;
import simulator.result.manager.impl.ResultManagerImpl;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatorManagerImpl implements SimulatorManager {

    private EstablishmentManager establishmentManager;
    private ManualSimulationSetupManager manualSimulationSetupManager;
    private WorldBuilderManager worldBuilderManager;
    private SimulatorExecutionManager simulatorExecutionManager;
    private InformationManager infoManager;
    private ResultManager simulatorResultManager;

    public SimulatorManagerImpl() {
        this.worldBuilderManager = new WorldBuilderManagerImpl();
        this.simulatorExecutionManager = new SimulatorExecutionManagerImpl();
        this.infoManager = new InformationManagerImpl();
        this.establishmentManager = new EstablishmentManagerImpl();
        this.manualSimulationSetupManager = new ManualSimulationSetupManagerImpl();
        this.simulatorResultManager = new ResultManagerImpl();
    }

    @Override
    public void buildSimulationWorld(String filePath) {
        this.worldBuilderManager.buildSimulationWorld(filePath);
        this.simulatorExecutionManager
                .initThreadPoolService(
                        this.worldBuilderManager
                                .getWorldDefinition()
                                .getThreadCountDefinition()
                                .getThreadCount());
    }

    @Override
    public SimulationDetailsDto getSimulationWorldDetails() {
        return worldBuilderManager.getSimulationWorldDetails();
    }

    @Override
    public EnvironmentPropertiesDto getEnvironmentPropertiesDefinition() {
        return worldBuilderManager.getEnvironmentPropertiesDefinition();
    }

    @Override
    public void setSelectedEnvironmentPropertiesValue(String propName, String type, String value) {
        manualSimulationSetupManager
                .setSelectedEnvironmentPropertiesValue(
                        worldBuilderManager.getWorldDefinition(),
                        propName,
                        type,
                        value
                );
    }

    @Override
    public SimulationDocumentInfoDto runSimulator() {

        establishmentManager.establishSimulation(this.worldBuilderManager.getWorldDefinition());
        SimulationDocument simulationDocument = infoManager.createNewSimulationDocument(
                worldBuilderManager.getWorldDefinition(), establishmentManager.getEstablishedWorldInstance());

        simulatorExecutionManager.runSimulator(simulationDocument);
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
    public List<String> getAllEntities() {
        return new ArrayList<>(this.worldBuilderManager
                .getWorldDefinition()
                .getEntities()
                .keySet());
    }

    @Override
    public List<String> getAllProperties() {
        Map<String, String> propertiesMap = new HashMap<>();
        List<String> propertiesList = new ArrayList<>();

        this.worldBuilderManager.getWorldDefinition().getEntities().keySet().forEach(
                entity -> insertEntityPropertiesToMapByEntityName(propertiesMap, entity));
        propertiesList.addAll(propertiesMap.keySet());
        return propertiesList;
    }

    @Override
    public List<String> getPropertiesByEntity(String entityName) {
        List<String> properties = new ArrayList<>();
        properties.addAll(this.worldBuilderManager.getWorldDefinition().getEntities().get(entityName).getProperties().keySet());

        return properties;
    }

    private void insertEntityPropertiesToMapByEntityName(Map<String, String> propertiesMap, String entity) {
        this.worldBuilderManager.getWorldDefinition().getEntities().get(entity).getProperties().keySet().forEach(property->propertiesMap.put(property, property));
    }

    @Override
    public void setEntityDefinitionPopulation(String entityName, Integer entityPopulation) {
        manualSimulationSetupManager
                .setEntityDefinitionPopulation(
                        worldBuilderManager.getWorldDefinition(), entityName, entityPopulation);

    }

    @Override
    public <T> void setEnvironmentPropertyValue(String envPropertyName, T envPropertyValue) {
        this.manualSimulationSetupManager.setEnvironmentPropertyValue(
                this.worldBuilderManager.getWorldDefinition(), envPropertyName, envPropertyValue);
    }

    @Override
    public List<EnvironmentPropertyDto> getAllEnvironmentProperties() {
        return this.worldBuilderManager.getAllEnvironmentProperties();
    }

    @Override
    public void resetSingleEntityPopulation(String entityName) {
        this.manualSimulationSetupManager
                .resetSingleEntityPopulation(this.worldBuilderManager.getWorldDefinition(), entityName);
    }

    @Override
    public void resetSingleEnvironmentVariable(String envVarName) {
        this.manualSimulationSetupManager
                .resetSingleEnvironmentVariable(this.worldBuilderManager.getWorldDefinition(), envVarName);
    }

    @Override
    public Integer getMaxPopulationSize() {
        return this.worldBuilderManager.getMaxPopulationSize();
    }

    @Override
    public void resetAllManualSetup() {
        this.manualSimulationSetupManager.resetAllManualSetup(this.worldBuilderManager.getWorldDefinition());
    }

    @Override
    public SimulationDocumentInfoDto stopSimulationByGuid(String guid) {
        this.simulatorExecutionManager.stopSimulation(this.infoManager.getSimulationDocumentByGuid(guid));
        return this.infoManager.getLatestSimulationDocumentInfoDto(guid);
    }

    @Override
    public SimulationDocumentInfoDto pauseSimulationByGuid(String guid) {
        this.simulatorExecutionManager.pauseSimulation(this.infoManager.getSimulationDocumentByGuid(guid));
        return this.infoManager.getLatestSimulationDocumentInfoDto(guid);
    }

    @Override
    public SimulationDocumentInfoDto resumeSimulationByGuid(String guid) {
        this.simulatorExecutionManager.resumeSimulation(this.infoManager.getSimulationDocumentByGuid(guid));
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
    public ResultManager getSimulatorResultManager() {
        return simulatorResultManager;
    }
}
