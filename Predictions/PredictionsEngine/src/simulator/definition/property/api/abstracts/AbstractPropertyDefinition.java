package simulator.definition.property.api.abstracts;

import simulator.definition.property.api.interfaces.PropertyDefinition;
import enums.PropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public abstract class AbstractPropertyDefinition<T> implements PropertyDefinition<T> {
    private final String name;
    private final PropertyType propertyType;
    private ValueGenerator<T> valueGenerator;

    public AbstractPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<T> valueGenerator) {
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
    public PropertyType getType() {
        return propertyType;
    }
    @Override
    public T generateValue() {
        return valueGenerator.generateValue();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("property name: ").append(name).append(System.lineSeparator());
        sb.append("property type: ").append(propertyType.toString()).append(System.lineSeparator());
        sb.append("value initialization type: ").append(valueGenerator.toString()).append(System.lineSeparator());

        return sb.toString();
    }
}
