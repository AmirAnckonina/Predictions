package simulator.builder.mainBuilder.utils;

import simulator.builder.mainBuilder.api.WorldBuilder;
import simulator.builder.mainBuilder.utils.enums.eBuilderDataSrcType;
import simulator.builder.mainBuilder.utils.exception.UnsupportedBuilderTypeException;
import simulator.builder.mainBuilder.impl.xml.XmlWorldBuilder;

public interface SimulationBuilderFactory {
    static WorldBuilder createSimulationBuilder(eBuilderDataSrcType dataSrcType) throws UnsupportedBuilderTypeException {
        switch (dataSrcType) {
            case XML:
                return new XmlWorldBuilder();
            default:
                throw new UnsupportedBuilderTypeException("The given data source type is unspported by the simulation builder");
        }
    }
}
