package simulator.builder.world.utils.factory;

import simulator.builder.world.api.interfaces.WorldBuilder;
import simulator.builder.world.utils.file.enums.eDataFileType;
import simulator.builder.world.impl.xml.XmlWorldBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.impl.WorldBuilderContextValidatorImpl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface WorldBuilderFactory {
    static WorldBuilder createSimulationBuilder(eDataFileType dataSrcType, String filePath) {
        switch (dataSrcType) {
            case XML:
                return new XmlWorldBuilder(filePath, new WorldBuilderContextValidatorImpl());
            case JSON:
                throw new NotImplementedException();
            default:
                throw new WorldBuilderException(
                        "The given data source type is unsupported by the simulation builder");
        }
    }
}
