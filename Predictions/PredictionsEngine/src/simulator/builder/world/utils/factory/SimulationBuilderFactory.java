package simulator.builder.world.utils.factory;

import simulator.builder.world.api.WorldBuilder;
import simulator.builder.world.utils.enums.eBuilderDataSrcType;
import simulator.builder.world.utils.exception.UnsupportedBuilderTypeException;
import simulator.builder.world.impl.xml.XmlWorldBuilder;

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
