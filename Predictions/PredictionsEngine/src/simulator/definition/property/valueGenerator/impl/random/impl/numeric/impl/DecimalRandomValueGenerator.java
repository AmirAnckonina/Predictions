package simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl;

import structure.range.Range;
import simulator.definition.property.valueGenerator.impl.random.impl.numeric.api.AbstractNumericRandomValueGenerator;

public class DecimalRandomValueGenerator extends AbstractNumericRandomValueGenerator<Integer> {

    public DecimalRandomValueGenerator(Range<Integer> range) {
        super(range);
    }
    public DecimalRandomValueGenerator() {
        this(null);
    }

    @Override
    public Integer generateValue()  {
        if (getRange().isPresent()) {
            return range.getFrom() + random.nextInt(range.getTo() - range.getFrom());
        } else {
            return random.nextInt();
        }
    }
}
