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
import java.util.List;

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
        worldBuilderManager.buildSimulationWorld(filePath);
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
    public SimulationEndDto runSimulator() {

        establishmentManager.establishSimulation(this.worldBuilderManager.getWorldDefinition());
        SimulationDocument simulationDocument
                 = infoManager.createNewSimulationDocument(
                         worldBuilderManager.getWorldDefinition(), establishmentManager.getEstablishedWorldInstance()
        );

        return simulatorExecutionManager.runSimulator(simulationDocument);
    }

    @Override
    public void exitSimulator() {
        throw new SimulatorRunnerException("Not Impl Exit Simulator method");
    }

    @Override
    public ResultManager getSimulatorResultManagerImpl() {
        // WTF!!!!!!!!
        return this.simulatorResultManager;
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

}
