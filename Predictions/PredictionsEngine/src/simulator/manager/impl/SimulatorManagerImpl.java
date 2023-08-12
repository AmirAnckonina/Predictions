package simulator.manager.impl;

import dto.EnvironmentPropertiesDto;
import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eBuilderDataSrcType;
import simulator.builder.world.utils.factory.SimulationBuilderFactory;
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
    @Override
    public BuildSimulatorDto buildSimulator(String filePath) {

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
    public Object getSimulationDetails() {
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
    public Object setActiveEnvironmentProperties(EnvironmentPropertiesDto data) {
        return null;
    }

    @Override
    public Object activeEnvironment(EnvironmentPropertiesDto data) {
        // instances = manager.instance.createInstances();
        // env = manager.activateEnvironment(dto );
        // manager.initializeRunner(instances, env);
        // manager.run();
        return null;
    }
}
