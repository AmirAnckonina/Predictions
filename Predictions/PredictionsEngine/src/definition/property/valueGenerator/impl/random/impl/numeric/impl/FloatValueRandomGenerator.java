package definition.property.valueGenerator.impl.random.impl.numeric.impl;

import definition.property.valueGenerator.impl.random.impl.numeric.api.BaseNumericRandomValueGenerator;

public class FloatValueRandomGenerator extends BaseNumericRandomValueGenerator<Float> {

    public FloatValueRandomGenerator(Float from, Float to) {
        super(from, to);
    }
    @Override
    public Float generateValue()  {

        return from + ((to - from) * random.nextFloat());
    }
}
