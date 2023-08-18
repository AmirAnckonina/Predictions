package simulator.definition.property.api.interfaces;

import simulator.definition.property.utils.enums.ePropertyType;

public interface PropertyDefinition<T> {
    String getName();
    ePropertyType getType();
    T generateValue();
}
