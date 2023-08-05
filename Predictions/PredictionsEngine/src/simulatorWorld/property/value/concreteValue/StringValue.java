package simulatorWorld.property.value.concreteValue;

import simulatorWorld.property.value.abstractValue.PropertyValue;

public class StringValue extends PropertyValue {
    private String initValue;

    public StringValue(String initValue, Boolean randomInitialize) {
        super(randomInitialize);
        this.initValue = initValue;
    }
}
