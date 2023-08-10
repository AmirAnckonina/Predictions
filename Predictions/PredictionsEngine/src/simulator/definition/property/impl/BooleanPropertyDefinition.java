package simulator.definition.property.impl;

import simulator.definition.property.api.BasePropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class BooleanPropertyDefinition extends BasePropertyDefinition<Boolean> {
    // Consider add propType in c'tor.
    public BooleanPropertyDefinition(String name, ValueGenerator<Boolean> valueGenerator) {
        super(name, ePropertyType.BOOLEAN, valueGenerator);
    }

}
