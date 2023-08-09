package simulator.impl;

import dto.BuildSimulatorDto;
import dto.LoadSimulationFileDto;
import dto.SimulationDetailsDto;
import response.SimulatorResponse;
import simulator.api.Simulator;

public class SimulatorImpl implements Simulator {

    @Override
    public BuildSimulatorDto buildSimulator(SimulationDetailsDto simulationDetails) {

        BuildSimulatorDto buildSimulatorResult;

        // loadFile - FileLoader? file validation? fileName, path, etc..
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
