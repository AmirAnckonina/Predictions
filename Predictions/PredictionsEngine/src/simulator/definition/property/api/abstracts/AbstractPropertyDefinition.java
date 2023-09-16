package simulator.definition.property.api.abstracts;

import simulator.definition.property.api.interfaces.PropertyDefinition;
import enums.PropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;
import simulator.definition.property.valueGenerator.impl.fixed.FixedValueGenerator;

public abstract class AbstractPropertyDefinition<T> implements PropertyDefinition<T> {
    private final String name;
    private final PropertyType propertyType;
    private ValueGenerator<T> activeValueGenerator;
    private final ValueGenerator<T> defaultValueGenerator;

    public AbstractPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<T> activeValueGenerator) {
        this.name = name;
        this.propertyType = propertyType;
        this.activeValueGenerator = activeValueGenerator;
        this.defaultValueGenerator = activeValueGenerator;
    }

    @Override
    public void setFixedValueGenerator(T fixedValue) {
        this.activeValueGenerator = new FixedValueGenerator<>(fixedValue);
    }

    @Override
    public void resetToDefaultValueGenerator() {
        this.activeValueGenerator = this.defaultValueGenerator;
    }

    @Override
    public String getName() {return name; }

    @Override
    public PropertyType getType() {
        return propertyType;
    }
    @Override
    public T generateValue() {
        return activeValueGenerator.generateValue();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("property name: ").append(name).append(System.lineSeparator());
        sb.append("property type: ").append(propertyType.toString()).append(System.lineSeparator());
        sb.append("value initialization type: ").append(activeValueGenerator.toString()).append(System.lineSeparator());

        return sb.toString();
    }
}
