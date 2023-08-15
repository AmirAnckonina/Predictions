package simulator.manager.impl;

import response.SimulatorResponse;
import simulator.definition.environment.Environment;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.impl.BooleanPropertyDefinition;
import simulator.definition.property.impl.FloatPropertyDefinition;
import simulator.definition.property.impl.IntegerPropertyDefinition;
import simulator.definition.property.impl.StringPropertyDefinition;
import simulator.definition.property.valueGenerator.impl.fixed.FixedValueGenerator;
import simulator.definition.world.World;
import simulator.manager.api.EnvironmentManager;

import java.util.List;

public class EnvironmentManagerImpl implements EnvironmentManager {
    @Override
    public void addPropertyInstance(String propName, ePropertyType type, String value, World world) {
        switch (type){
            case DECIMAL:
            case FLOAT:
                FloatPropertyDefinition floatProperty = new FloatPropertyDefinition(propName,
                        new FixedValueGenerator<Float>(Float.parseFloat(value)));
                break;
            case BOOLEAN:
                BooleanPropertyDefinition booleanProperty = new BooleanPropertyDefinition(propName,
                        new FixedValueGenerator<Boolean>(Boolean.parseBoolean(value)));
                break;
            case STRING:
                StringPropertyDefinition stringProperty = new StringPropertyDefinition(propName,
                        new FixedValueGenerator<String>(value));
                break;
            case INTEGER:
                IntegerPropertyDefinition integerProperty = new IntegerPropertyDefinition(propName,
                        new FixedValueGenerator<Integer>(Integer.parseInt(value)));
                break;
        }
    }

    @Override
    public SimulatorResponse<String> setRandomValuesForUninitializedProperties(List<Integer> propertiesUserUpdatedList,
                                                                               World world) {
        Environment environment = world.getEnvironment();


        return null;
    }

}
