package definition.property.valueGenerator.impl.random.impl.numeric.api;

import definition.property.valueGenerator.impl.random.api.BaseRandomValueGenerator;

public abstract class BaseNumericRandomValueGenerator<T> extends BaseRandomValueGenerator<T> {

    protected final T from;
    protected final T to;

    protected BaseNumericRandomValueGenerator(T from, T to) {
        this.from = from;
        this.to = to;
    }

}


