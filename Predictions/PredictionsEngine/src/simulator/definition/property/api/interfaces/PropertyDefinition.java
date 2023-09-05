package simulator.definition.property.api.interfaces;

import simulator.definition.property.utils.enums.PropertyType;

public interface PropertyDefinition<T> {
    String getName();
    PropertyType getType();
    T generateValue();
    String toString();
}
