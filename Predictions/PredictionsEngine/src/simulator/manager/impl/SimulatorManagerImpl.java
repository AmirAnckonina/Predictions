package simulator.manager.impl;

import dto.EnvironmentPropertiesDto;
import dto.SetPropertySimulatorResponseDto;
import dto.builder.params.enums.eSetPropertyStatus;
import response.SimulatorResponse;
import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eBuilderDataSrcType;
import simulator.builder.world.utils.factory.SimulationBuilderFactory;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.world.World;
import dto.BuildSimulatorDto;
import simulator.manager.api.SimulatorManager;
import simulator.manager.utils.SimulatorUtils;

import java.io.File;

public class SimulatorManagerImpl implements SimulatorManager {

    private final SimulatorUtils utils;
    private World world;
    private WorldBuilder worldBuilder;

    public SimulatorManagerImpl() {
        this.utils = new SimulatorUtils();
    }

    private void loadedSimulation(){
        // Load instances
    };

    @Override
    public BuildSimulatorDto buildSimulationWorld(String filePath) {

        BuildSimulatorDto buildSimulatorResult;

        // loadFile - FileLoader? file validation? fileName, path, etc...
        try {
            File simulationConfigFile = utils.getFileByPath(filePath);
            eBuilderDataSrcType dataSrcType = utils.getDataSrcTypeByFileExtention(filePath);
            worldBuilder = SimulationBuilderFactory.createSimulationBuilder(dataSrcType);
            world = worldBuilder.buildWorld();


        } catch(Exception ex) {

        }


        // set builderConfig:
        // - give the builder "context" like file
        // - so it could be build from the file content
        // build - validate content in xml - like dupliccation, unsupported fields, etc.
        // build - Mapping generated xml classes into definition classes
        // get "world" definition
        // build dto and return

        return null;
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

        } catch (Exception e) {
            SetPropertySimulatorResponseDto response = new SetPropertySimulatorResponseDto(eSetPropertyStatus.FAILED, e.getMessage());
            return new SimulatorResponse(false, e.getMessage());
        }
        return null;
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
