package simulator.builder.world.utils.factory;

import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eDataFileType;
import simulator.builder.world.utils.exception.UnsupportedBuilderTypeException;
import simulator.builder.world.impl.xml.XmlWorldBuilder;
import simulator.builder.world.validator.impl.WorldContextBuilderHelperImpl;

public interface SimulationBuilderFactory {
    static WorldBuilder createSimulationBuilder(eDataFileType dataSrcType, String filePath) {
        switch (dataSrcType) {
            case XML:
                return new XmlWorldBuilder(filePath, new WorldContextBuilderHelperImpl());
            case JSON:
                return null;
            default:
                throw new UnsupportedBuilderTypeException("The given data source type is unspported by the simulation builder");
        }
    }
}
