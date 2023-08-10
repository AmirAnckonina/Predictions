package simulator.definition.property.impl;

import simulator.definition.property.api.BasePropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class IntegerPropertyDefinition extends BasePropertyDefinition<Integer> {
    // Consider add propType in c'tor.
    public IntegerPropertyDefinition(String name, ValueGenerator<Integer> valueGenerator) {
        super(name, ePropertyType.DECIMAL, valueGenerator);
    }

}
