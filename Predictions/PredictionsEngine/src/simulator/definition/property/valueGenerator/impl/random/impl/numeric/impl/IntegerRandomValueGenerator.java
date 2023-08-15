package simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl;

import simulator.definition.property.valueGenerator.impl.random.impl.numeric.api.AbstractNumericRandomValueGenerator;

public class IntegerRandomValueGenerator extends AbstractNumericRandomValueGenerator<Integer> {

    public IntegerRandomValueGenerator(Integer from, Integer to) {

        super(from, to);
    }
    @Override
    public Integer generateValue()  {
        return from + random.nextInt(to);
    }
}
