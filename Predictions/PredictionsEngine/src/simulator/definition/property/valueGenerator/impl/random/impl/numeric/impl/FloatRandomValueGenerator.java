package simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl;

import simulator.definition.property.valueGenerator.impl.random.impl.numeric.api.AbstractNumericRandomValueGenerator;

public class FloatRandomValueGenerator extends AbstractNumericRandomValueGenerator<Float> {

    public FloatRandomValueGenerator(Float from, Float to) {
        super(from, to);
    }
    @Override
    public Float generateValue()  {

        return from + ((to - from) * random.nextFloat());
    }
}
