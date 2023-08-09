package definition.property.api;

import definition.property.enums.ePropertyType;

public interface PropertyDefinition<T> {
    String getName();
    ePropertyType getType();
    T generateValue();
}
