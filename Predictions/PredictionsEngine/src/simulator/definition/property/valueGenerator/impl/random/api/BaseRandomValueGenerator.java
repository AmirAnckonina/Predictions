package simulator.definition.property.valueGenerator.impl.random.api;

import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.Random;

public abstract class BaseRandomValueGenerator<T> implements ValueGenerator<T> {
    protected final Random random;

    protected BaseRandomValueGenerator() {

        random = new Random();
    }

    @Override
    public String toString() {
        return "random";
    }
}
