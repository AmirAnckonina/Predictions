package simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl;

import simulator.definition.property.impl.Range;
import simulator.definition.property.valueGenerator.impl.random.impl.numeric.api.AbstractNumericRandomValueGenerator;

public class IntegerRandomValueGenerator extends AbstractNumericRandomValueGenerator<Integer> {

    public IntegerRandomValueGenerator(Range<Integer> range) {
        super(range);
    }
    public IntegerRandomValueGenerator() {
        super();
    }

    @Override
    public Integer generateValue()  {
        if (hasRange) {
            return range.getFrom() + random.nextInt(range.getTo());
        } else {
            return random.nextInt();
        }
    }
}
