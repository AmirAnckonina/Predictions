package simulator.manager.impl;

import dto.*;
import response.SimulatorResponse;
import simulator.builder.manager.api.SimulationBuilderManager;
import simulator.builder.manager.impl.SimulationBuilderManagerImpl;
import simulator.execution.establishment.api.SimulationEstablishmentManager;
import simulator.execution.establishment.impl.SimulationEstablishmentManagerImpl;
import simulator.execution.manager.api.SimulationExecutionManager;
import simulator.execution.manager.impl.SimulationExecutionManagerImpl;
import simulator.result.api.SimulationResult;
import simulator.result.newManager.SimulationInformationManager;
import simulator.result.newManager.SimulationInformationManagerImpl;
import simulator.manager.api.SimulatorManager;
import simulator.result.manager.api.SimulatorResultManager;
import simulator.result.manager.impl.SimulatorResultManagerImpl;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class SimulatorManagerImpl implements SimulatorManager {

    private SimulationEstablishmentManager simulationEstablishmentManager;
    private SimulationBuilderManager builderManager;
    private SimulationExecutionManager executionManager;
    private SimulationInformationManager infoManager;
    private SimulatorResultManager simulatorResultManager;

    public SimulatorManagerImpl() {
        this.builderManager = new SimulationBuilderManagerImpl();
        this.executionManager = new SimulationExecutionManagerImpl();
        this.infoManager = new SimulationInformationManagerImpl();
        this.simulationEstablishmentManager = new SimulationEstablishmentManagerImpl();
        this.simulatorResultManager = new SimulatorResultManagerImpl();
    }

    @Override
    public SimulatorResponse buildSimulationWorld(String filePath) {
        return builderManager.buildSimulationWorld(filePath);
    }

    @Override
    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails() {
        return builderManager.getSimulationWorldDetails();
    }

    @Override
    public SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition() {
        return builderManager.getEnvironmentPropertiesDefinition();
    }

    @Override
    public SimulatorResponse establishSimulation() {
        return executionManager.establishSimulation(
                builderManager.getWorldDefinition()
        );
    }

    @Override
    public SimulatorResponse setSelectedEnvironmentVariablesValue(String propName, String type, String value) {
        return executionManager.setSelectedEnvironmentVariablesValue(
                builderManager.getWorldDefinition(),
                propName, type, value);
    }

    @Override
    public SimulatorResponse<SimulationEndDto> runSimulator() {
        SimulationResult simulationResult = infoManager.setNewSimulationInfo(
                builderManager.getWorldDefinition(),
                executionManager.getWorldInstance()
        );
        return executionManager.runSimulator(simulationResult);
    }

    @Override
    public SimulatorResponse exitSimulator() {

        throw new SimulatorRunnerException("Not Impl Exit Simulator method");
    }

    @Override
    public SimulatorResultManager getSimulatorResultManagerImpl() {
        // WTF!!!!!!!!
        return this.simulatorResultManager;
    }


    @Override
    public SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo() {
        return executionManager.getEstablishedEnvironmentInfo();
    }

}
