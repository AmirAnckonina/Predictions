package simulator.definition.property.impl;

import simulator.definition.property.api.AbstractNumericPropertyDefinition;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.Optional;

public class IntegerPropertyDefinition extends AbstractNumericPropertyDefinition<Integer> {

    public IntegerPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Integer> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }

    public IntegerPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Integer> valueGenerator, boolean hasRange, Range<Integer> range) {
        super(name, propertyType, valueGenerator, hasRange, range);
    }
}
