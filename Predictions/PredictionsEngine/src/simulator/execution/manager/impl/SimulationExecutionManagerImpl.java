package simulator.execution.manager.impl;

import dto.EstablishedEnvironmentInfoDto;
import dto.SetPropertySimulatorResponseDto;
import dto.SimulationEndDto;
import dto.enums.eSetPropertyStatus;
import response.SimulatorResponse;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.world.WorldDefinition;
import simulator.execution.establishment.api.EnvironmentSetupManager;
import simulator.execution.establishment.api.SimulationEstablishmentManager;
import simulator.execution.establishment.impl.EnvironmentSetupManagerImpl;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.manager.api.SimulationExecutionManager;
import simulator.result.api.SimulationResult;
import simulator.runner.api.SimulatorRunner;
import simulator.runner.impl.SimulatorRunnerImpl;

import java.util.*;

public class SimulationExecutionManagerImpl implements SimulationExecutionManager {
    private WorldInstance worldInstance;
    private SimulationEstablishmentManager establishmentManager;
    private EnvironmentSetupManager environmentSetupManager;
    private List<String> propertiesUpdatedByUser;
    SimulatorRunner simulatorRunner;

    public SimulationExecutionManagerImpl() {
        this.propertiesUpdatedByUser = new ArrayList<>();
    }

    @Override
    public SimulatorResponse establishSimulation(WorldDefinition worldDefinition) {
        try {

            this.worldInstance =
                    establishmentManager.establishSimulation(worldDefinition);

            return  new SimulatorResponse<>(
                    true,
                    "Establishment done successfully");

        } catch (Exception e) {

            return  new SimulatorResponse<>(
                    false,
                    "An issue detected while trying to establish simulation.");
        }
    }

    @Override
    public SimulatorResponse setSelectedEnvironmentVariablesValue(WorldDefinition worldDefinition, String propName, String type, String value) {
        SetPropertySimulatorResponseDto responseDto;
        SimulatorResponse response;
        try {
            ePropertyType eType = ePropertyType.STRING;
            switch (type.toLowerCase())
            {
                case "string":
                case "str":
                    eType = ePropertyType.STRING;
                    break;
                case "float":
                    eType = ePropertyType.FLOAT;
                    break;
                case "decimal":
                case "int":
                case "integer":
                    eType = ePropertyType.DECIMAL;
                    break;
                case "boolean":
                case "bool":
                    eType = ePropertyType.BOOLEAN;
                    break;
            }

            // Should change to SimulatorEnvironmentManager????? data member!!!
            environmentSetupManager = new EnvironmentSetupManagerImpl();
            environmentSetupManager.setFixedValuePropertyDefinition(
                    propName, eType, value, worldDefinition.getEnvironment()
            );

            responseDto = new SetPropertySimulatorResponseDto(
                    eSetPropertyStatus.SUCCEEDED,
                    "Environment Variable Value has been set with " + value.toString());

            response =  new SimulatorResponse(
                    true,
                    "Environment Variable Value has been set with " + value.toString(),
                    responseDto);
            this.propertiesUpdatedByUser.add(propName);

        } catch (Exception e) {

            responseDto = new SetPropertySimulatorResponseDto(eSetPropertyStatus.FAILED, e.getMessage());
            response =  new SimulatorResponse(false, e.getMessage(), responseDto);
        }
        return response;
    }

    @Override
    public SimulatorResponse<SimulationEndDto> runSimulator(SimulationResult simulationResult) {

        try {
            this.simulatorRunner = new SimulatorRunnerImpl(this.worldInstance);
            this.simulatorRunner.run(simulationResult);

            //need to add DTO to return termination reason.
            return new SimulatorResponse<SimulationEndDto>(
                    true,
                    "run succesffuly, ID : " + simulationResult.getSimulationUuid(),
                    new SimulationEndDto(
                            simulationResult.getSimulationUuid(),
                            simulationResult.getTerminationReason().toString()
                    )
            );

        } catch (Exception e) {

            return new SimulatorResponse(
                    false,
                    "An error detected while running the simulation, couldn't complete the simulation"
            );
        }
    }

    @Override
    public SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo() {

        try {

            Map<String, String> envInfo =
                    establishmentManager.getEstablishedEnvironmentInfo(worldInstance);

            return new SimulatorResponse<>(
                    true,
                    new EstablishedEnvironmentInfoDto(envInfo)
            );

        } catch (Exception e) {

            return new SimulatorResponse<>(
                    false,
                    "An issue detected while trying to get environment properties instances info."
            );
        }
    }

    @Override
    public WorldInstance getWorldInstance() {
        return this.worldInstance;
    }
}
