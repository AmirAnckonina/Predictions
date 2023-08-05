package simulatorWorld.property.value.concreteValue;

import simulatorWorld.property.value.abstractValue.PropertyValue;

public class FloatValue extends PropertyValue {
    private Float initValue;

    public FloatValue(Float initValue, Boolean randomInitialize) {
        super(randomInitialize);
        this.initValue = initValue;
    }
}
