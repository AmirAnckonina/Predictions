package simulator.definition.property.valueGenerator.impl.random.impl.numeric.api;

import simulator.definition.property.impl.Range;
import simulator.definition.property.valueGenerator.impl.random.api.BaseRandomValueGenerator;

public abstract class AbstractNumericRandomValueGenerator<T extends Number> extends BaseRandomValueGenerator<T> {

    protected Range<T> range;
    protected boolean hasRange;

    protected AbstractNumericRandomValueGenerator(Range range) {
        this.hasRange = true;
        this.range = range;
    }

    public AbstractNumericRandomValueGenerator() {
        this.hasRange = false;
        this.range = null;
    }
}


