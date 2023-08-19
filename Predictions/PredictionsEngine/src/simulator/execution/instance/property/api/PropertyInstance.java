package simulator.execution.instance.property.api;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.api.interfaces.PropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;

public interface PropertyInstance {
    AbstractPropertyDefinition getPropertyDefinition();
    Object getValue();
    void updateValue(Object val);
}
