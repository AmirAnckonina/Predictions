package definition.property.valueGenerator.impl.fixed;

import definition.property.valueGenerator.api.ValueGenerator;

public class FixedValueGenerator<T> implements ValueGenerator<T> {
    private final T fixedValue;

    public FixedValueGenerator(T fixedValue) {
        this.fixedValue = fixedValue;
    }

    @Override
    public T generateValue() {
        return fixedValue;
    }
}
