package simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl;

import simulator.definition.property.impl.Range;
import simulator.definition.property.valueGenerator.impl.random.impl.numeric.api.AbstractNumericRandomValueGenerator;

public class FloatRandomValueGenerator extends AbstractNumericRandomValueGenerator<Float> {
    public FloatRandomValueGenerator(Range<Float> range) {

        super(range);
    }

    public FloatRandomValueGenerator() {
        super();
    }

    @Override
    public Float generateValue()  {
        if (getRange().isPresent()) {
            return range.getFrom() + (range.getTo() - range.getFrom()) * random.nextFloat();
        } else {
            return random.nextInt() + random.nextFloat();
        }
    }
}
