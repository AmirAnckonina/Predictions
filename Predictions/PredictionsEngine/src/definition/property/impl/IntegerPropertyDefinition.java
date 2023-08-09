package definition.property.impl;

import definition.property.api.BasePropertyDefinition;
import definition.property.enums.ePropertyType;
import definition.property.valueGenerator.api.ValueGenerator;

public class IntegerPropertyDefinition extends BasePropertyDefinition<Integer> {
    // Consider add propType in c'tor.
    public IntegerPropertyDefinition(String name, ValueGenerator<Integer> valueGenerator) {
        super(name, ePropertyType.DECIMAL, valueGenerator);
    }

}
