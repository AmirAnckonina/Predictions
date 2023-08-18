package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class IntegerPropertyDefinition extends AbstractNumericPropertyDefinition<Integer> {

    public IntegerPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Integer> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }

    public IntegerPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Integer> valueGenerator, Range<Integer> range) {
        super(name, propertyType, valueGenerator, range);
    }
}
