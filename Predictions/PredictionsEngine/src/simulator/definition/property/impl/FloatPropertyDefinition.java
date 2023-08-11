package simulator.definition.property.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import simulator.definition.property.api.BasePropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class FloatPropertyDefinition extends BasePropertyDefinition<Float> {
    // Consider add propType in c'tor.
    public FloatPropertyDefinition(String name, ValueGenerator<Float> valueGenerator) {
        super(name, ePropertyType.FLOAT, valueGenerator);
    }

}
