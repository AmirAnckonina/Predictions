package definition.property.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import definition.property.api.BasePropertyDefinition;
import definition.property.enums.ePropertyType;
import definition.property.valueGenerator.api.ValueGenerator;

public class BooleanPropertyDefinition extends BasePropertyDefinition<Boolean> {
    // Consider add propType in c'tor.
    public BooleanPropertyDefinition(String name, ValueGenerator<Boolean> valueGenerator) {
        super(name, ePropertyType.BOOLEAN, valueGenerator);
    }

}
