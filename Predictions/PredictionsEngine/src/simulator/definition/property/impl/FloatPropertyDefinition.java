package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import enums.PropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;
import structure.range.Range;

public class FloatPropertyDefinition extends AbstractNumericPropertyDefinition<Float> {

    public FloatPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<Float> valueGenerator) {
        this(name, propertyType, valueGenerator, null);
    }

    public FloatPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<Float> valueGenerator, Range<Float> range) {
        super(name, propertyType, valueGenerator, range);
    }
}
