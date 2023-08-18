package simulator.definition.property.api.abstracts;

import simulator.definition.property.api.interfaces.PropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public abstract class AbstractPropertyDefinition<T> implements PropertyDefinition<T> {
    private final String name;
    private final ePropertyType propertyType;
    private ValueGenerator<T> valueGenerator;

    public AbstractPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<T> valueGenerator) {
        this.name = name;
        this.propertyType = propertyType;
        this.valueGenerator = valueGenerator;
    }
    public void setValueGenerator(ValueGenerator<T> valueGenerator) {

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
