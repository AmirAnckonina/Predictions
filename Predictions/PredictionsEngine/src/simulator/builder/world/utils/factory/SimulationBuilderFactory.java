package simulator.builder.world.utils.factory;

import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eBuilderDataSrcType;
import simulator.builder.world.utils.exception.UnsupportedBuilderTypeException;
import simulator.builder.world.impl.xml.XmlWorldBuilder;

import java.io.File;

public interface SimulationBuilderFactory {
    static WorldBuilder createSimulationBuilder(eBuilderDataSrcType dataSrcType, File simulationConfigFile) throws UnsupportedBuilderTypeException {
        switch (dataSrcType) {
            case XML:
                return new XmlWorldBuilder(simulationConfigFile);
            case JSON:
                return null;
            default:
                throw new UnsupportedBuilderTypeException("The given data source type is unspported by the simulation builder");
        }
    }
}
