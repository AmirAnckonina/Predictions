package simulator.execution.instance.property.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.execution.instance.property.api.PropertyInstance;

public class PropertyInstanceImpl implements PropertyInstance {
    private AbstractPropertyDefinition propertyDefinition;
    private Object value;
    private int lastTickUpdate;

    public PropertyInstanceImpl(AbstractPropertyDefinition propertyDefinition, Object value) {
        this.propertyDefinition = propertyDefinition;
        this.value = value;
    }

    @Override
    public AbstractPropertyDefinition getPropertyDefinition() {
        return propertyDefinition;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void updateValue(Object val) {
        this.value = val;
    }
}
