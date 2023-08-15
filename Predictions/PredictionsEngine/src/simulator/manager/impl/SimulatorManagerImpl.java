package simulator.manager.impl;

import dto.EnvironmentPropertiesDto;
import dto.SetPropertySimulatorResponseDto;
import dto.builder.params.enums.eSetPropertyStatus;
import response.SimulatorResponse;
import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eBuilderDataSrcType;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.utils.factory.SimulationBuilderFactory;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.world.World;
import dto.BuildSimulatorDto;
import simulator.manager.api.SimulatorManager;
import simulator.manager.utils.SimulatorUtils;

import java.io.File;

public class SimulatorManagerImpl implements SimulatorManager {
    private World world;
    private WorldBuilder worldBuilder;

    public SimulatorManagerImpl() {
    }

    private void loadedSimulation(){
        // Load instances
    };

    @Override
    public SimulatorResponse buildSimulationWorld(String filePath) {

        BuildSimulatorDto buildSimulatorResult;

        try {
            File simulationConfigFile = SimulatorUtils.getFileByPath(filePath);
            eBuilderDataSrcType dataSrcType = SimulatorUtils.getDataSrcTypeByFileExtention(filePath);
            worldBuilder = SimulationBuilderFactory.createSimulationBuilder(dataSrcType, simulationConfigFile);
            world = worldBuilder.buildWorld();
            return new SimulatorResponse(true, "the following file has loaded successfully" + filePath);
        } catch(Exception ex) {
            return new SimulatorResponse(false, ex.getMessage());
        }
    }


    @Override
    public Object getSimulationWorldDetails() {
        return null;
    }

    @Override
    public Object runSimulator(Integer simulationId) {
        return null;
    }

    @Override
    public Object exitSimulator() {
        return null;
    }

    @Override
    public EnvironmentPropertiesDto getEnvironmentProperties() {
        return null;
    }

    @Override
    public SimulatorResponse setEnvironmentVariableValue(String propName, String type, String value) {
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
                case "decimal":
                case "double":
                    eType = ePropertyType.DECIMAL;
                    break;
                case "boolean":
                case "bool":
                    eType = ePropertyType.BOOLEAN;
                    break;
                case "integer":
                case "int":
                    eType = ePropertyType.INTEGER;
                    break;
            }
            EnvironmentManagerImpl environmentManager = new EnvironmentManagerImpl();
            environmentManager.addPropertyInstance(propName, eType, value, this.world);

            responseDto = new SetPropertySimulatorResponseDto(eSetPropertyStatus.SUCCEEDED,
                    "Environment Variable Value has been set with " + value);
            response =  new SimulatorResponse(true,
                    "Environment Variable Value has been set with " + value,
                                responseDto);

        } catch (Exception e) {
            responseDto = new SetPropertySimulatorResponseDto(eSetPropertyStatus.FAILED, e.getMessage());
            response =  new SimulatorResponse(false, e.getMessage(), responseDto);
        }
        return response;
    }

    @Override
    public Object activateEnvironment() {
        // instances = manager.instance.createInstances();
        // env = manager.activateEnvironment(dto );
        // manager.initializeRunner(instances, env);
        // manager.run();
        return null;
    }


}
