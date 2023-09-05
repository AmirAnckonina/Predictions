package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class DecimalPropertyDefinition extends AbstractNumericPropertyDefinition<Integer> {

    public DecimalPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<Integer> valueGenerator) {
        this(name, propertyType, valueGenerator, null);
    }

    public DecimalPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<Integer> valueGenerator, Range<Integer> range) {
        super(name, propertyType, valueGenerator, range);
    }
}
