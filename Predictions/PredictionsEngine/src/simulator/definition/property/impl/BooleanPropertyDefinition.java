package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class BooleanPropertyDefinition extends AbstractPropertyDefinition<Boolean> {

    public BooleanPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Boolean> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }
}
