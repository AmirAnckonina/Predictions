package simulatorWorld.property.value.abstractValue;

public abstract class PropertyValue {
    private Boolean randomInitialize;

    public PropertyValue(Boolean randomInitialize) {
        this.randomInitialize = randomInitialize;
    }

    public PropertyValue() {
        this.randomInitialize = false;
    }
}
