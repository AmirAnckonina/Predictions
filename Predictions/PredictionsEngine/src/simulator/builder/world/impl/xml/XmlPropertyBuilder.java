package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDProperty;
import resources.jaxb.schema.generated.PRDRange;
import simulator.builder.world.api.AbstractPropertyBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.impl.*;
import simulator.definition.property.valueGenerator.api.ValueGenerator;
import simulator.definition.property.valueGenerator.utils.factory.ValueGeneratorFactory;

public class XmlPropertyBuilder extends AbstractPropertyBuilder {
    private PRDProperty generatedEntityProperty;
    private PRDEnvProperty generatedEnvironmentProperty;

    public XmlPropertyBuilder(PRDEnvProperty generatedEnvironmentProperty) {
        this.generatedEnvironmentProperty = generatedEnvironmentProperty;
    }
    public XmlPropertyBuilder(PRDProperty generatedEntityProperty) {
        this.generatedEntityProperty = generatedEntityProperty;
    }

    @Override
    public AbstractPropertyDefinition buildEntityProperty() {
        // Should deal with ranged property nicely -> think about it later
        return buildAbstractPropertyDefinitionByGeneratedData(
                generatedEntityProperty.getPRDName(),
                generatedEntityProperty.getType(),
                generatedEntityProperty.getPRDRange());
    }

    @Override
    public AbstractPropertyDefinition buildEnvironmentProperty() {
        // Should deal with ranged property nicely -> think about it later
        return buildAbstractPropertyDefinitionByGeneratedData(
                generatedEnvironmentProperty.getPRDName(),
                generatedEnvironmentProperty.getType(),
                generatedEnvironmentProperty.getPRDRange());
    }

    private AbstractPropertyDefinition buildAbstractPropertyDefinitionByGeneratedData(String prdName, String prdType, PRDRange prdRange) {
        String propName = prdName;
        ePropertyType propType = ePropertyType.valueOf(prdType);
        Range range = null;
        if (prdRange != null) {
            double from = prdRange.getFrom();
            double to = prdRange.getTo();
            range = new Range(from, to);
        }

        return buildProperty(propName, propType, range);
    }

    private AbstractPropertyDefinition buildConcretePropertyByType(String name, ePropertyType propType, Range range) {
        //Should impl the randomGenerator for numeric types with Optional<Integer>
        AbstractPropertyDefinition newPropDefinition = null;
        switch (propType) {

            case BOOLEAN:
                return new BooleanPropertyDefinition(
                        name, ePropertyType.BOOLEAN, ValueGeneratorFactory.createRandomBooleanGenerator());

            case STRING:
                return new StringPropertyDefinition(
                        name, ePropertyType.STRING, ValueGeneratorFactory.createRandomStringGenerator());

            case DECIMAL:

                if (range != null) {
                    Range intRange = new Range((Integer) range.getFrom(), (Integer) range.getTo());
                    ValueGenerator integerValGen = ValueGeneratorFactory.createRandomRangedIntegerGenerator(intRange);

                    return new IntegerPropertyDefinition(
                            name, ePropertyType.INTEGER, integerValGen);

                } else {
                    return new IntegerPropertyDefinition(
                            name, ePropertyType.INTEGER, ValueGeneratorFactory.createRandomUnlimitedIntegerGenerator());
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
