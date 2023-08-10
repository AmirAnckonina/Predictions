package simulator.definition.property.api;

import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class BasePropertyDefinition<T> implements PropertyDefinition<T> {
    private final String name;
    private final ePropertyType propertyType;
    private final ValueGenerator<T> valueGenerator;

    public BasePropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<T> valueGenerator) {
        this.name = name;
        this.propertyType = propertyType;
        this.valueGenerator = valueGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ePropertyType getType() {
        return propertyType;
    }
    @Override
    public T generateValue() {
        return valueGenerator.generateValue();
    }
}
