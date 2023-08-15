package simulator.definition.property.impl;

import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class StringPropertyDefinition extends AbstractPropertyDefinition<String> {
    // Consider add propType in c'tor.
    public StringPropertyDefinition(String name, ValueGenerator<String> valueGenerator) {
        super(name, ePropertyType.STRING, valueGenerator);
    }

}
