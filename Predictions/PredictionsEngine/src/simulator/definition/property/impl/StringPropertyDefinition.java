package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import enums.PropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class StringPropertyDefinition extends AbstractPropertyDefinition<String> {

    public StringPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<String> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }
}
