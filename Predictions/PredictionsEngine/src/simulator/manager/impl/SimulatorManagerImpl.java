package simulator.manager.impl;

import dto.*;
import dto.builder.params.BasePropertyDto;
import dto.builder.params.enums.eSetPropertyStatus;
import response.SimulatorResponse;
import simulator.builder.world.api.interfaces.WorldBuilder;
import simulator.builder.world.utils.file.enums.eDataFileType;
import simulator.builder.world.utils.factory.WorldBuilderFactory;
import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.impl.Range;
import simulator.definition.rule.Rule;
import simulator.definition.world.World;
import simulator.establishment.api.SimulationEstablishmentManager;
import simulator.establishment.impl.SimulationEstablishmentManagerImpl;
import simulator.execution.runner.api.SimulatorRunner;
import simulator.execution.runner.impl.SimulatorRunnerImpl;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.manager.api.EnvironmentManager;
import simulator.manager.api.SimulationResult;
import simulator.manager.api.SimulatorManager;
import simulator.builder.world.utils.file.WorldBuilderFileUtils;
import simulator.manager.api.SimulatorResultManager;
import simulator.manager.utils.SimulatorUtils;
import simulator.result.impl.SimulationInitialInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class SimulatorManagerImpl implements SimulatorManager {

    private SimulationEstablishmentManager simulationEstablishmentManager;
    private World worldDefinition;
    private WorldInstance establishedWorldInstance;
    private WorldBuilder worldBuilder;
    private List<String> propertiesUpdatedByUser;
    private EnvironmentManager environmentManager;
    private String simulationID;
    private SimulatorRunner simulatorRunner;
    private SimulatorResultManager simulatorResultManager;

    //SimulatorResultManager simulatorResultManager;

    public SimulatorManagerImpl() {
        //this.simulatorResultManager = new  SimulatorResultManagerImpl();
        this.propertiesUpdatedByUser = new ArrayList<>();
        this.simulationEstablishmentManager = new SimulationEstablishmentManagerImpl();
        this.simulatorResultManager = new SimulatorResultManagerImpl();

    }

    @Override
    public SimulatorResponse buildSimulationWorld(String filePath) {

        BuildSimulatorDto buildSimulatorResult;

        try {
            eDataFileType dataSrcType = WorldBuilderFileUtils.getDataFileTypeByFileExtension(filePath);
            worldBuilder = WorldBuilderFactory.createSimulationBuilder(dataSrcType, filePath);
            worldDefinition = worldBuilder.buildWorld();
            return new SimulatorResponse(true, "the following file has loaded successfully: " + filePath);

        } catch (Exception ex) {

            return new SimulatorResponse(false, ex.getMessage());
        }
    }


    @Override
    public SimulatorResponse<SimulationDetailsDto> getSimulationWorldDetails() {

        try {
            String entitiesInfo = worldDefinition.getPrimaryEntity().toString();
            StringBuilder rulesSb = new StringBuilder();
            for (Rule rule : worldDefinition.getRules()) {
                rulesSb.append(rule.toString()).append(System.lineSeparator());
            }

            String rulesInfo = rulesSb.toString();
            String terminationInfo = worldDefinition.getTermination().toString();

            return new SimulatorResponse<>(
                    true,
                    new SimulationDetailsDto(entitiesInfo, rulesInfo, terminationInfo)
            );

        } catch (Exception e) {

            return new SimulatorResponse<>(
                    false,
                    "An error occured while trying to fetch the the loaded simulation world details."
            );
        }

    }

    @Override
    public SimulatorResponse establishSimulation() {
        try {

            this.establishedWorldInstance =
                    simulationEstablishmentManager.establishSimulation(this.worldDefinition);

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
    public SimulatorResponse<String> runSimulator() {

        try {
            String guid = SimulatorUtils.getGUID();
            SimulationInitialInfo simulationInitialInfo =
                    new SimulationInitialInfo(
                            guid,
                            worldDefinition.getPrimaryEntity().getName(),
                            establishedWorldInstance.getPrimaryEntityInstances().size(),
                            worldDefinition.getPrimaryEntity().getProperties().keySet(),
                            establishedWorldInstance
                    );
            SimulationResult simulationResult = new SimulationResultImpl(simulationInitialInfo);
            this.simulatorRunner = new SimulatorRunnerImpl(this.establishedWorldInstance);
            this.simulatorRunner.run(simulationResult);
            simulatorResultManager.addSimulationResult(
                    simulationResult.getSimulationUuid(), simulationResult
            );

            return new SimulatorResponse<String>(
                    true,
                    "run succesffuly, ID : " + guid,
                    guid
            );
        } catch (Exception e) {

            return new SimulatorResponse(
                    false,
                    "An error detected while running the simulation, couldn't complete the simulation"
            );
        }
    }

    @Override
    public SimulatorResponse exitSimulator() {
        throw new NotImplementedException();
    }

    @Override
    public SimulatorResultManager getSimulatorResultManagerImpl() {
        return this.simulatorResultManager;
    }

    @Override
    public SimulatorResponse startEnvironmentSession() {
        // might be used in the future
        SimulatorResponse response = new SimulatorResponse<>(true, "session started successfully",
                null);
        return response;
    }

    @Override
    public SimulatorResponse endEnvironmentSession() {
        return new SimulatorResponse<>(true, "session ended successfully", null);
    }

    @Override
    public SimulatorResponse endLoadingSimulationSessionSignal() {
        SimulatorResponse response = new SimulatorResponse<>(
                true, "end loading simulation successfully",
                null);
        return response;
    }

    @Override
    public SimulatorResponse startLoadingSimulationSessionSignal() {
        SimulatorResponse response = new SimulatorResponse<>(true, "start loading simulation successfully",
                null);
        return response;
    }

    @Override
    public SimulatorResponse<EstablishedEnvironmentInfoDto> getEstablishedEnvironmentInfo() {

        try {

            Map<String, String> envInfo =
                    simulationEstablishmentManager.getEstablishedEnvironmentInfo(establishedWorldInstance);

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
    public SimulatorResponse<EnvironmentPropertiesDto> getEnvironmentPropertiesDefinition() {
        try {

            List<BasePropertyDto> propertyDtoList = new ArrayList<>();

            for (String propertyName : this.worldDefinition.getEnvironment().getPropertiesNames()) {

                AbstractPropertyDefinition property = this.worldDefinition.getEnvironment().getPropertyByName(propertyName);
                if (property instanceof AbstractNumericPropertyDefinition) {
                    Range range = (Range) ((AbstractNumericPropertyDefinition) property).getRange().orElse(null);
                    if (range != null) {
                        BasePropertyDto propertyDto = new BasePropertyDto(propertyName,
                                property.getType().toString(), range.getFrom().toString(),
                                range.getTo().toString(), null);
                        propertyDtoList.add(propertyDto);
                        continue;
                    }
                }
                BasePropertyDto propertyDto = new BasePropertyDto(propertyName,
                        property.getType().toString(), null, null, null);
                propertyDtoList.add(propertyDto);
            }
            EnvironmentPropertiesDto envPropsDto = new EnvironmentPropertiesDto(propertyDtoList);

            return new SimulatorResponse<>(true, envPropsDto);

        } catch (Exception e) {
            return new SimulatorResponse<>(
                    false, "an error detcetd while trying to set env properties.");
        }
    }

    @Override
    public SimulatorResponse setSelectedEnvironmentVariablesValue(String propName, String type, String value) {
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
            environmentManager = new EnvironmentManagerImpl();
            environmentManager.setFixedValuePropertyDefinition(propName, eType, value, this.worldDefinition.getEnvironment());

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



}
