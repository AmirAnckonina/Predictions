package simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl;

import structure.range.Range;
import simulator.definition.property.valueGenerator.impl.random.impl.numeric.api.AbstractNumericRandomValueGenerator;

public class FloatRandomValueGenerator extends AbstractNumericRandomValueGenerator<Float> {
    public FloatRandomValueGenerator(Range<Float> range) {

        super(range);
    }

    public FloatRandomValueGenerator() {

        this(null);
    }

    @Override
    public Float generateValue()  {
        if (getRange().isPresent()) {
           // return range.getFrom() + (range.getTo() - range.getFrom()) * random.nextFloat();
            return range.getFrom() + random.nextFloat() * (range.getTo() - range.getFrom());
        } else {
            return random.nextInt() + random.nextFloat();
        }
    }
}
