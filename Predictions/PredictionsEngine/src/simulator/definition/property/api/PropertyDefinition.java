package simulator.definition.property.api;

import simulator.definition.property.enums.ePropertyType;

public interface PropertyDefinition<T> {
    String getName();
    ePropertyType getType();
    T generateValue();
}
