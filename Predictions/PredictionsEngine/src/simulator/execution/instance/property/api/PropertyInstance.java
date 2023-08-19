package simulator.execution.instance.property.api;

import simulator.definition.property.api.interfaces.PropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;

public interface PropertyInstance<T> {
    String getPropertyDefinition();
    T getValue();
    void updateValue(T val);
    ePropertyType getPropertyType();
}
