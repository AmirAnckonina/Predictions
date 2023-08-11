package definition.property.impl;

import definition.property.api.BasePropertyDefinition;
import definition.property.enums.ePropertyType;
import definition.property.valueGenerator.api.ValueGenerator;

public class StringPropertyDefinition extends BasePropertyDefinition<String> {
    // Consider add propType in c'tor.
    public StringPropertyDefinition(String name, ValueGenerator<String> valueGenerator) {
        super(name, ePropertyType.STRING, valueGenerator);
    }

}
