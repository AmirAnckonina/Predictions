package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class FloatPropertyDefinition extends AbstractNumericPropertyDefinition<Float> {

    public FloatPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Float> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }

    public FloatPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Float> valueGenerator, Range<Float> range) {
        super(name, propertyType, valueGenerator, range);
    }
}
