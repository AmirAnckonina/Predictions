package simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl;

import simulator.definition.property.valueGenerator.impl.random.impl.numeric.api.BaseNumericRandomValueGenerator;

public class IntegerRandomValueGenerator extends BaseNumericRandomValueGenerator<Integer> {

    public IntegerRandomValueGenerator(Integer from, Integer to) {

        super(from, to);
    }
    @Override
    public Integer generateValue()  {
        return from + random.nextInt(to);
    }
}
