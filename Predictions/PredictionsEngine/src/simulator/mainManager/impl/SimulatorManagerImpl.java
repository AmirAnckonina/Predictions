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
    public SimulatorResponse buildSimulationWorld(String filePath) {
        return worldBuilderManager.buildSimulationWorld(filePath);
    }

    @Override
    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails() {
        return worldBuilderManager.getSimulationWorldDetails();
    }

    @Override
    public SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition() {
        return worldBuilderManager.getEnvironmentPropertiesDefinition();
    }

    @Override
    public SimulatorResponse establishSimulation() {
        return establishmentManager.establishSimulation(worldBuilderManager.getWorldDefinition());
    }

    @Override
    public SimulatorResponse setSelectedEnvironmentPropertiesValue(String propName, String type, String value) {
        return manualSimulationSetupManager
                .setSelectedEnvironmentPropertiesValue(
                        worldBuilderManager.getWorldDefinition(),
                        propName,
                        type,
                        value
                );
    }

    @Override
    public SimulatorResponse setEntityDefinitionPopulation(WorldDefinition worldDefinition, String entityName, Integer entityPopulation) {
        return manualSimulationSetupManager
                .setEntityDefinitionPopulation(
                        worldBuilderManager.getWorldDefinition(), entityName, entityPopulation);

    }

    @Override
    public SimulatorResponse<SimulationEndDto> runSimulator() {
         SimulationDocument simulationDocument
                 = infoManager.createNewSimulationDocument(
                         worldBuilderManager.getWorldDefinition(), executionManager.getWorldInstance()
         );

        return executionManager.runSimulator(simulationDocument);
    }

    @Override
    public SimulatorResponse exitSimulator() {

        throw new SimulatorRunnerException("Not Impl Exit Simulator method");
    }

    @Override
    public ResultManager getSimulatorResultManagerImpl() {
        // WTF!!!!!!!!
        return this.simulatorResultManager;
    }


    @Override
    public SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo() {
        return establishmentManager.getEstablishedEnvironmentInfo();
    }

}
