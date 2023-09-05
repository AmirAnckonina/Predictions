package simulator.builder.utils.factory;

import simulator.builder.api.interfaces.WorldBuilder;
import simulator.builder.impl.xml.XmlWorldBuilder;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.validator.impl.WorldBuilderContextValidatorImpl;
import simulator.builder.utils.file.enums.DataFileType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface WorldBuilderFactory {
    static WorldBuilder createSimulationBuilder(DataFileType dataSrcType, String filePath) {
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
