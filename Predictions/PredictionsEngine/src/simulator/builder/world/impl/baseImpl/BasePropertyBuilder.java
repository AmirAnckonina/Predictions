package simulator.builder.world.impl.baseImpl;

import simulator.builder.world.api.interfaces.PropertyBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.impl.*;
import simulator.definition.property.valueGenerator.api.ValueGenerator;
import simulator.definition.property.valueGenerator.utils.factory.ValueGeneratorFactory;

public class BasePropertyBuilder implements PropertyBuilder {

    @Override
    public AbstractPropertyDefinition buildProperty(String name, ePropertyType propertyType, Range range) {
        AbstractPropertyDefinition newPropDefinition = null;
        switch (propertyType) {

            case BOOLEAN:
                return new BooleanPropertyDefinition(
                        name, ePropertyType.BOOLEAN, ValueGeneratorFactory.createRandomBooleanGenerator());

            case STRING:
                return new StringPropertyDefinition(
                        name, ePropertyType.STRING, ValueGeneratorFactory.createRandomStringGenerator());

            case DECIMAL:

                if (range != null) {
                    Range intRange = new Range(Double.valueOf((double)range.getFrom()).intValue(),
                            Double.valueOf((double)range.getTo()).intValue());
                    ValueGenerator integerValGen = ValueGeneratorFactory.createRandomRangedIntegerGenerator(intRange);

                    return new DecimalPropertyDefinition(
                            name, ePropertyType.DECIMAL, integerValGen);

                } else {
                    return new DecimalPropertyDefinition(
                            name, ePropertyType.DECIMAL, ValueGeneratorFactory.createRandomUnlimitedDecimalGenerator());
                }


            case FLOAT:
                if (range != null) {
                    Range floatRange = new Range((Float) range.getFrom(), (Float) range.getTo());
                    ValueGenerator floatValGen = ValueGeneratorFactory.createRandomRangedFloatGenerator(floatRange);

                    return new FloatPropertyDefinition(
                            name, ePropertyType.FLOAT, floatValGen);

                } else {
                    return new FloatPropertyDefinition(
                            name, ePropertyType.FLOAT, ValueGeneratorFactory.createRandomUnlimitedFloatGenerator());
                }

            default:
                throw new WorldBuilderException("Unsupported property type");
        }
    }
}
