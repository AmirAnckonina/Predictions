package simulator.manager.impl;

import simulator.builder.mainBuilder.api.WorldBuilder;
import simulator.builder.mainBuilder.utils.enums.eBuilderDataSrcType;
import simulator.builder.mainBuilder.utils.SimulationBuilderFactory;
import simulator.definition.world.World;
import dto.BuildSimulatorDto;
import simulator.manager.api.Simulator;
import simulator.manager.utils.SimulatorUtils;

import java.io.File;

public class SimulatorImpl implements Simulator {

    private final SimulatorUtils utils;
    private World worldContext;
    private WorldBuilder simulationBuilder;

    public SimulatorImpl() {
        this.utils = new SimulatorUtils();
    }
    @Override
    public BuildSimulatorDto buildSimulator(String filePath) {

        BuildSimulatorDto buildSimulatorResult;

        // loadFile - FileLoader? file validation? fileName, path, etc...
        try {
            File simulationConfigFile = utils.getFileByPath(filePath);
            eBuilderDataSrcType dataSrcType = utils.getDataSrcTypeByFileExtention(filePath);
            simulationBuilder = SimulationBuilderFactory.createSimulationBuilder(dataSrcType);
            worldContext = simulationBuilder.buildWorld();


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
    public Object runSimulator() {
        return null;
    }

    @Override
    public Object exitSimulator() {
        return null;
    }
}
