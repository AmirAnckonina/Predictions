package simulator.establishment.impl;

import simulator.definition.environment.Environment;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.impl.*;
import simulator.definition.property.valueGenerator.impl.fixed.FixedValueGenerator;
import simulator.definition.world.World;
import simulator.establishment.api.EnvironmentSetupManager;

import java.util.Optional;

public class EnvironmentSetupManagerImpl implements EnvironmentSetupManager {

    public void setFixedValuePropertyDefinition(String propName, ePropertyType type, String value, World world)
    {
        setFixedValuePropertyDefinition(propName, type, value, world.getEnvironment());
    }

    @Override
    public void setFixedValuePropertyDefinition(String propName, ePropertyType type, String value, Environment environment) {
        AbstractPropertyDefinition propertyDefinition = environment.getPropertyByName(propName);
        if(type == ePropertyType.BOOLEAN){
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
            environment.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(Boolean.parseBoolean(value)));
        } else if (type == ePropertyType.FLOAT) {
            FloatPropertyDefinition floatPropertyDefinition = (FloatPropertyDefinition)propertyDefinition;
            Optional<Range<Float>> optionalRange = floatPropertyDefinition.getRange();
            if(optionalRange.isPresent()){
                Range<Float> range = optionalRange.get();
                Float valueInFloat = Float.parseFloat(value);
                if(valueInFloat > range.getTo() || valueInFloat < range.getFrom()){
                    throw new RuntimeException("Invalid input");
                }
            }
            environment.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(Float.parseFloat(value)));
        } else if (type == ePropertyType.DECIMAL) {
            DecimalPropertyDefinition decimalPropertyDefinition = (DecimalPropertyDefinition)propertyDefinition;
            Optional<Range<Integer>> optionalRange = decimalPropertyDefinition.getRange();

            if(optionalRange.isPresent()){
                Range<Integer> range = optionalRange.get();
                Integer valueInFloat = Integer.parseInt(value);
                if(valueInFloat > range.getTo() || valueInFloat < range.getFrom()){
                    throw new RuntimeException("Invalid input");
                }
            }
            environment.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(Integer.parseInt(value)));
        }
        else{
            environment.setValueGeneratorByPropertyName(propName, new FixedValueGenerator<>(value));
        }
    }

//    private <T> boolean isInRange(Optional<Range<T>>){
//
//    }

//    @Override
//    public SimulatorResponse<String> setRandomValuesForUninitializedProperties(List<String> propertiesUserUpdatedList,
//                                                                               Environment environment) {
//        for (String propertyName:environment.getPropertiesNames()
//             ) {
//            if(!propertiesUserUpdatedList.contains(propertyName)){
//                environment.getPropertyByName(propertyName).generateValue();
//            }
//        }
//
//        return null;
//    }

}