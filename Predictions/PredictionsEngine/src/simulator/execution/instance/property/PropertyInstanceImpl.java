package simulator.execution.instance.property;

import simulator.definition.property.api.interfaces.PropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.execution.context.api.PropertyInstance;

public class PropertyInstanceImpl<T> implements PropertyInstance<T> {
    private String propertyName;
    private T value;
    private ePropertyType propertyType;

    public PropertyInstanceImpl(String propertyName, T value, ePropertyType propertyType) {
        this.propertyName = propertyName;
        this.value = value;
        this.propertyType = propertyType;
    }

    public String getPropertyDefinition() {
        return propertyName;
    }

    public T getValue() {
        return value;
    }

    public void updateValue(T value) {
        this.value = value;
    }

    @Override
    public ePropertyType getPropertyType() {
        return this.propertyType;
    }
}
