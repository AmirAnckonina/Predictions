package simulator.definition.property.valueGenerator.impl.random.impl.numeric.api;

import simulator.definition.property.impl.Range;
import simulator.definition.property.valueGenerator.impl.random.api.BaseRandomValueGenerator;

import java.util.Optional;

public abstract class AbstractNumericRandomValueGenerator<T extends Number> extends BaseRandomValueGenerator<T> {

    protected final Range<T> range;

    protected AbstractNumericRandomValueGenerator(Range range) {
        this.range = range;
    }

    protected AbstractNumericRandomValueGenerator() {
        this(null);
    }
    public Optional<Range<T>> getRange() {
        return Optional.ofNullable(range);
    }
}


