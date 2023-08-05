package simulatorWorld.property.value.concreteValue;

import simulatorWorld.property.value.abstractValue.PropertyValue;

public class BooleanValue extends PropertyValue {
    private Boolean initValue;
    public BooleanValue(Boolean initValue, Boolean randomInitialize) {
        super(randomInitialize);
        this.initValue = initValue;
    }
}
