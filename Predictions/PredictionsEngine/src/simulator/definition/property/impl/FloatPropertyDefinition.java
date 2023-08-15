package simulator.definition.property.impl;

import simulator.definition.property.api.AbstractNumericPropertyDefinition;
import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.Optional;

public class FloatPropertyDefinition extends AbstractNumericPropertyDefinition<Float> {

    public FloatPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Float> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }

    public FloatPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Float> valueGenerator, boolean hasRange, Range<Float> range) {
        super(name, propertyType, valueGenerator, hasRange, range);
    }
}
