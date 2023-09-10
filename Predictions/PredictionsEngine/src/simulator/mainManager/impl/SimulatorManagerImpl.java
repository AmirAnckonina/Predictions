package simulator.mainManager.impl;

import dto.*;
import response.SimulatorResponse;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.builder.manager.impl.WorldBuilderManagerImpl;
import simulator.definition.world.WorldDefinition;
import simulator.information.simulationDocument.api.SimulationDocument;
import simulator.manualSetup.manager.api.ManualSimulationSetupManager;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.manualSetup.manager.impl.ManualSimulationSetupManagerImpl;
import simulator.establishment.manager.impl.EstablishmentManagerImpl;
import simulator.execution.manager.api.ExecutionManager;
import simulator.execution.manager.impl.ExecutionManagerImpl;
import simulator.result.api.SimulationResult;
import simulator.information.manager.api.InformationManager;
import simulator.information.manager.impl.InformationManagerImpl;
import simulator.mainManager.api.SimulatorManager;
import simulator.result.manager.api.ResultManager;
import simulator.result.manager.impl.ResultManagerImpl;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class SimulatorManagerImpl implements SimulatorManager {

    private EstablishmentManager establishmentManager;
    private ManualSimulationSetupManager manualSimulationSetupManager;
    private WorldBuilderManager worldBuilderManager;
    private ExecutionManager executionManager;
    private InformationManager infoManager;
    private ResultManager simulatorResultManager;

    public SimulatorManagerImpl() {
        this.worldBuilderManager = new WorldBuilderManagerImpl();
        this.executionManager = new ExecutionManagerImpl();
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
    public void establishSimulation() {
        establishmentManager.establishSimulation(worldBuilderManager.getWorldDefinition());
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
    public void setEntityDefinitionPopulation(String entityName, Integer entityPopulation) {
         manualSimulationSetupManager
                .setEntityDefinitionPopulation(
                        worldBuilderManager.getWorldDefinition(), entityName, entityPopulation);

    }

    @Override
    public SimulationEndDto runSimulator() {
         SimulationDocument simulationDocument
                 = infoManager.createNewSimulationDocument(
                         worldBuilderManager.getWorldDefinition(), executionManager.getWorldInstance()
         );

        return executionManager.runSimulator(simulationDocument);
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

}
