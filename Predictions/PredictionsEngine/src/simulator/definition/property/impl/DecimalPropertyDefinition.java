package simulator.definition.property.impl;

import simulator.definition.property.api.abstracts.AbstractNumericPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class DecimalPropertyDefinition extends AbstractNumericPropertyDefinition<Integer> {

    public DecimalPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Integer> valueGenerator) {
        super(name, propertyType, valueGenerator);
    }

    public DecimalPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Integer> valueGenerator, Range<Integer> range) {
        super(name, propertyType, valueGenerator, range);
    }
}
