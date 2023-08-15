package simulator.definition.property.valueGenerator.impl.random.impl.numeric.api;

import simulator.definition.property.valueGenerator.impl.random.api.BaseRandomValueGenerator;

public abstract class AbstractNumericRandomValueGenerator<T> extends BaseRandomValueGenerator<T> {

    protected final T from;
    protected final T to;

    protected AbstractNumericRandomValueGenerator(T from, T to) {
        this.from = from;
        this.to = to;
    }

}


