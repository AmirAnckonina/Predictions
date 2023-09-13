package simulator.definition.property.api.interfaces;

import enums.PropertyType;

public interface PropertyDefinition<T> {
    String getName();
    PropertyType getType();
    T generateValue();
    String toString();
}
