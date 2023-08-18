package simulator.execution.context.api;

import simulator.definition.property.api.interfaces.PropertyDefinition;

public interface PropertyInstance {
    PropertyDefinition getPropertyDefinition();
    Object getValue();
    void updateValue(Object val);
}
