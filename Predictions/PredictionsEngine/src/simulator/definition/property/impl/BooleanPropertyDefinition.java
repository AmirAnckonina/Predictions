package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import enums.PropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class BooleanPropertyDefinition extends AbstractPropertyDefinition<Boolean> {

    public BooleanPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<Boolean> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }
}
