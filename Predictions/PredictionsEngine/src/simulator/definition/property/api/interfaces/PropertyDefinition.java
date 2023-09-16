package simulator.definition.property.api.interfaces;

import enums.PropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public interface PropertyDefinition<T> {
    String getName();
    PropertyType getType();
    T generateValue();
    String toString();
    void setFixedValueGenerator(T fixedValue);
    void resetToDefaultValueGenerator();
}
