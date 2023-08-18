package simulator.execution.instance.property;

import simulator.definition.property.api.interfaces.PropertyDefinition;
import simulator.execution.context.api.PropertyInstance;

public class PropertyInstanceImpl implements PropertyInstance {
    private PropertyDefinition propertyDefinition;
    private Object value;

    public PropertyInstanceImpl(PropertyDefinition propertyDefinition, Object value) {
        this.propertyDefinition = propertyDefinition;
        this.value = value;
    }

    public PropertyDefinition getPropertyDefinition() {
        return propertyDefinition;
    }

    public Object getValue() {
        return value;
    }

    public void updateValue(Object value) {
        this.value = value;
    }
}
