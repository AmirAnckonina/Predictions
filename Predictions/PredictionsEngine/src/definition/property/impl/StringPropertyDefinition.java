package definition.property.impl;

import simulator.definition.property.api.BasePropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class StringPropertyDefinition extends BasePropertyDefinition<String> {
    // Consider add propType in c'tor.
    public StringPropertyDefinition(String name, ValueGenerator<String> valueGenerator) {
        super(name, ePropertyType.STRING, valueGenerator);
    }

}
