package definition.property.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import definition.property.api.BasePropertyDefinition;
import definition.property.enums.ePropertyType;
import definition.property.valueGenerator.api.ValueGenerator;

public class FloatPropertyDefinition extends BasePropertyDefinition<Float> {
    // Consider add propType in c'tor.
    public FloatPropertyDefinition(String name, ValueGenerator<Float> valueGenerator) {
        super(name, ePropertyType.FLOAT, valueGenerator);
    }

}

