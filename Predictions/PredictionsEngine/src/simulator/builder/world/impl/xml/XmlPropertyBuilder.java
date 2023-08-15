package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDProperty;
import resources.jaxb.schema.generated.PRDRange;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.PropertyBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.impl.BooleanPropertyDefinition;
import simulator.definition.property.impl.FloatPropertyDefinition;
import simulator.definition.property.impl.IntegerPropertyDefinition;
import simulator.definition.property.impl.StringPropertyDefinition;
import simulator.definition.property.valueGenerator.utils.factory.ValueGeneratorFactory;

public class XmlPropertyBuilder extends AbstractFileComponentBuilder implements PropertyBuilder {
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
        return buildAbstractPropertyDefinition(
                generatedEntityProperty.getPRDName(),
                generatedEntityProperty.getType(),
                generatedEntityProperty.getPRDRange());
    }

    @Override
    public AbstractPropertyDefinition buildEnvironmentProperty() {
        // Should deal with ranged property nicely -> think about it later
        return buildAbstractPropertyDefinition(
                generatedEnvironmentProperty.getPRDName(),
                generatedEnvironmentProperty.getType(),
                generatedEnvironmentProperty.getPRDRange());
    }

    private AbstractPropertyDefinition buildAbstractPropertyDefinition(String prdName, String prdType, PRDRange prdRange) {
        String propName = prdName;
        ePropertyType propType = ePropertyType.valueOf(prdType);
        if (prdRange != null) {
            double from = prdRange.getFrom();
            double to = prdRange.getTo();
        }

        return buildConcretePropertyByType(propName, propType);
    }

    private AbstractPropertyDefinition buildConcretePropertyByType(String name, ePropertyType propType) {
        //Should impl the randomGenerator for numeric types with Optional<Integer>
        AbstractPropertyDefinition newPropDefinition = null;
        switch (propType) {
            case BOOLEAN:
                return new BooleanPropertyDefinition(name, ValueGeneratorFactory.createRandomBooleanGenerator());
            case STRING:
                return new StringPropertyDefinition(name, ValueGeneratorFactory.createRandomStringGenerator());
            case DECIMAL:
                return new IntegerPropertyDefinition(name, ValueGeneratorFactory.createRandomIntegerGenerator(0, 0));
            case FLOAT:
                return new FloatPropertyDefinition(name, ValueGeneratorFactory.createRandomFloatGenerator(0f ,0f));
            default:
                throw new WorldBuilderException("Unsupported property type");
        }

    }





}
