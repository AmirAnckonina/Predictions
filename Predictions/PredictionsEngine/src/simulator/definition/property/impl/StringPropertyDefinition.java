package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class StringPropertyDefinition extends AbstractPropertyDefinition<String> {

    public StringPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<String> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }
}
