package simulator.execution.instance.property.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.execution.instance.property.api.PropertyInstance;

public class PropertyInstanceImpl implements PropertyInstance {
    private AbstractPropertyDefinition propertyDefinition;
    private Object value;
    private Integer lastTickUpdate;

    public PropertyInstanceImpl(AbstractPropertyDefinition propertyDefinition, Object value, Integer lastTickUpdate) {
        this.propertyDefinition = propertyDefinition;
        this.value = value;
        this.lastTickUpdate = lastTickUpdate;
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
    public void updateValue(Object val, int currTick) {
        this.value = val;
        this.lastTickUpdate = currTick;
    }

    @Override
    public Integer getLastTickUpdate() {
        return this.lastTickUpdate;
    }
}
