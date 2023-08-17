package simulator.builder.world.utils.factory;

import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eDataFileType;
import simulator.builder.world.impl.xml.XmlWorldBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.impl.WorldBuilderContextValidatorImpl;

public interface WorldBuilderFactory {
    static WorldBuilder createSimulationBuilder(eDataFileType dataSrcType, String filePath) {
        switch (dataSrcType) {
            case XML:
                return new XmlWorldBuilder(filePath, new WorldBuilderContextValidatorImpl());
            case JSON:
                return null;
            default:
                throw new WorldBuilderException(
                        "The given data source type is unsupported by the simulation builder");
        }
    }
}