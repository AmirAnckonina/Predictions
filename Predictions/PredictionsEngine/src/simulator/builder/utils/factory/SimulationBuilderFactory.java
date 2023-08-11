package simulator.builder.utils.factory;

import simulator.builder.api.WorldBuilder;
import simulator.builder.utils.enums.eBuilderDataSrcType;
import simulator.builder.utils.exception.UnsupportedBuilderTypeException;
import simulator.builder.impl.xml.XmlWorldBuilder;

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
