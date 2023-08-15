package simulator.definition.property.impl;

import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;
import simulator.definition.property.valueGenerator.utils.factory.ValueGeneratorFactory;

public class BooleanPropertyDefinition extends AbstractPropertyDefinition<Boolean> {
    // Consider add propType in c'tor.
    public BooleanPropertyDefinition(String name, ValueGenerator<Boolean> valueGenerator) {
        super(name, ePropertyType.BOOLEAN, valueGenerator);
    }

}
