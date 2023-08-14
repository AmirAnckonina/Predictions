package simulator.definition.property.valueGenerator.utils.factory;

import simulator.definition.property.valueGenerator.api.ValueGenerator;
import simulator.definition.property.valueGenerator.impl.fixed.FixedValueGenerator;
import simulator.definition.property.valueGenerator.impl.random.impl.bool.BooleanRandomValueGenerator;
import simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl.FloatRandomValueGenerator;
import simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl.IntegerRandomValueGenerator;
import simulator.definition.property.valueGenerator.impl.random.impl.string.StringRandomValueGenerator;

public interface ValueGeneratorFactory {
    static <T> ValueGenerator<T> createFixed(T value) {
        return new FixedValueGenerator<T>(value);
    }

    static ValueGenerator<Boolean> createRandomBooleanGenerator() {
        return new BooleanRandomValueGenerator();
    }

    static ValueGenerator<String> createRandomStringGenerator() { return new StringRandomValueGenerator();}

    static ValueGenerator<Integer> createRandomIntegerGenerator(Integer from, Integer to) {
        return new IntegerRandomValueGenerator(from, to);
    }
    static ValueGenerator<Float> createRandomFloatGenerator(Float from, Float to) {
        return new FloatRandomValueGenerator(from, to);
    }

}
