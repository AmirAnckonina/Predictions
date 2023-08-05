package simulatorWorld.property.value.concreteValue;

import simulatorWorld.property.value.abstractValue.PropertyValue;

public class IntegerValue extends PropertyValue {
    private int initValue;

    public IntegerValue(int initValue, Boolean randomInitialize) {
        super(randomInitialize);
        this.initValue = initValue;
    }
}
