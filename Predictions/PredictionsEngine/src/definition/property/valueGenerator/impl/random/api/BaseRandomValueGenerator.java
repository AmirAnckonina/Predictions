package definition.property.valueGenerator.impl.random.api;

import definition.property.valueGenerator.api.ValueGenerator;

import java.util.Random;

public abstract class BaseRandomValueGenerator<T> implements ValueGenerator<T> {
    protected final Random random;

    protected BaseRandomValueGenerator() {

        random = new Random();
    }
}
