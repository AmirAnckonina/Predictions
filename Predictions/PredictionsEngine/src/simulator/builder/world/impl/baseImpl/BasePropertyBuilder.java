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
    public AbstractPropertyDefinition buildProperty(
            String name, ePropertyType propertyType, Range doubledRange, String rawInitValue) {

        try {
            AbstractPropertyDefinition newPropDefinition = null;
            switch (propertyType) {

                case BOOLEAN:
                    return buildBooleanProperty(name, propertyType, doubledRange, rawInitValue);

                case STRING:
                    return buildStringProperty(name, propertyType, doubledRange, rawInitValue);

                case DECIMAL:
                    return buildDecimalProperty(name, propertyType, doubledRange, rawInitValue);

                case FLOAT:
                    return buildFloatProperty(name, propertyType, doubledRange, rawInitValue);

                default:
                    throw new WorldBuilderException("Unsupported property type");
            }
        } catch (Exception e) {
            throw new WorldBuilderException(
                    "An error detected while trying to build property definition: " + e.getMessage());
        }

    }

    public BasePropertyBuilder() {
        super();
    }


    @Override
    public AbstractPropertyDefinition buildBooleanProperty(
            String name, ePropertyType propertyType, Range doubledRange, String rawInitValue) {

        AbstractPropertyDefinition propDefinition;
        if (rawInitValue != null) {

            propDefinition = new BooleanPropertyDefinition(
                    name,
                    ePropertyType.BOOLEAN,
                    ValueGeneratorFactory.createFixed(Boolean.parseBoolean(rawInitValue)));
        } else {

            propDefinition = new BooleanPropertyDefinition(
                    name, ePropertyType.BOOLEAN, ValueGeneratorFactory.createRandomBooleanGenerator());
        }

        return propDefinition;
    }

    @Override
    public AbstractPropertyDefinition buildStringProperty(String name, ePropertyType propertyType, Range doubledRange, String rawInitValue) {

        AbstractPropertyDefinition propDefinition;

        if (rawInitValue != null) {
            propDefinition = new StringPropertyDefinition(
                    name, ePropertyType.STRING, ValueGeneratorFactory.createFixed(rawInitValue));
        } else {
            propDefinition = new StringPropertyDefinition(
                    name, ePropertyType.STRING, ValueGeneratorFactory.createRandomStringGenerator());
        }

        return propDefinition;
    }

    @Override
    public AbstractPropertyDefinition buildDecimalProperty(String name, ePropertyType propertyType, Range doubledRange, String rawInitValue) {
        AbstractPropertyDefinition propDefinition;
        ValueGenerator decimalValueGenerator;

        if (doubledRange != null) {
            Range decimalRange =
                    new Range(
                            doubledRange.getFrom().intValue(),
                            doubledRange.getTo().intValue()
                    );
            if (rawInitValue != null)
            {
                 decimalValueGenerator = ValueGeneratorFactory.createFixed(Integer.parseInt(rawInitValue));
            } else {
                decimalValueGenerator = ValueGeneratorFactory.createRandomRangedIntegerGenerator(decimalRange);
            }

            propDefinition =
                    new DecimalPropertyDefinition(
                            name, ePropertyType.DECIMAL, decimalValueGenerator, decimalRange);

        } else {

            if (rawInitValue != null)
            {
                decimalValueGenerator = ValueGeneratorFactory.createFixed(Integer.parseInt(rawInitValue));
            } else {
                decimalValueGenerator = ValueGeneratorFactory.createRandomUnlimitedDecimalGenerator();
            }
            propDefinition = new DecimalPropertyDefinition(
                    name, ePropertyType.DECIMAL, decimalValueGenerator);
        }

        return propDefinition;
    }

    @Override
    public AbstractPropertyDefinition buildFloatProperty(String name, ePropertyType propertyType, Range doubledRange, String rawInitValue) {

        AbstractPropertyDefinition propertyDefinition;
        ValueGenerator floatValGenerator;

        if (doubledRange != null) {
            Range floatRange =
                    new Range(
                            doubledRange.getFrom().floatValue(),
                            doubledRange.getTo().floatValue()
                    );

            if (rawInitValue != null)
            {
                floatValGenerator = ValueGeneratorFactory.createFixed(Float.parseFloat(rawInitValue));
            } else {
               floatValGenerator = ValueGeneratorFactory.createRandomRangedFloatGenerator(floatRange);
            }

            propertyDefinition = new FloatPropertyDefinition(
                    name, ePropertyType.FLOAT, floatValGenerator, floatRange);

        } else {
            if (rawInitValue != null)
            {
                floatValGenerator = ValueGeneratorFactory.createFixed(Float.parseFloat(rawInitValue));
            } else {
                floatValGenerator = ValueGeneratorFactory.createRandomUnlimitedFloatGenerator();
            }
            propertyDefinition = new FloatPropertyDefinition(
                    name, ePropertyType.FLOAT, floatValGenerator);
        }

        return propertyDefinition;
    }
}
