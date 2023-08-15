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
import java.util.Optional;

public class EnvironmentManagerImpl implements EnvironmentManager {

    public void addPropertyInstance(String propName, ePropertyType type, String value, World world)
    {
        AbstractPropertyDefinition propertyDefinition = world.getEnvironment().getPropertyByName(propName);
        if(propertyDefinition instanceof BooleanPropertyDefinition){
            switch (value){
                case "True":
                case "true":
                case "yes":
                case "y":
                case "false":
                case "False":
                case "No":
                case "no":
                    break;
                default:
                    throw new RuntimeException("Invalid input");

            }

        } else if (propertyDefinition instanceof FloatPropertyDefinition) {
            double from = Optional.ofNullable(((FloatPropertyDefinition)propertyDefinition).getFrom()).orElse(-1.1);
            //Float to = //((FloatPropertyDefinition)propertyDefinition).getTo();
            Float valueInFloat = Float.parseFloat(value);
            if(valueInFloat > to || valueInFloat < from){throw new RuntimeException("Invalid input");}

        } else if (propertyDefinition instanceof IntegerPropertyDefinition) {

        }
        addPropertyInstance(propName, type, value, world.getEnvironment());
    }

    @Override
    public void addPropertyInstance(String propName, ePropertyType type, String value, Environment environment) {
        switch (type){
            case DECIMAL:
            case FLOAT:
                environment.setPropertyValueByName(propName, new FixedValueGenerator<Float>(Float.parseFloat(value)));
                break;
            case BOOLEAN:
                environment.setPropertyValueByName(propName, new FixedValueGenerator<Boolean>(Boolean.parseBoolean(value)));
                break;
            case STRING:
                environment.setPropertyValueByName(propName, new FixedValueGenerator<String>(value));

                break;
            case INTEGER:
                environment.setPropertyValueByName(propName, new FixedValueGenerator<Integer>(Integer.parseInt(value)));
                break;
        }

    }

    @Override
    public SimulatorResponse<String> setRandomValuesForUninitializedProperties(List<String> propertiesUserUpdatedList,
                                                                               World world) {
        Environment environment = world.getEnvironment();


        return null;
    }

}
