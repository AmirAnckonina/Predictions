package simulator.definition.property.valueGenerator.utils.factory;

import simulator.definition.property.valueGenerator.api.ValueGenerator;
import simulator.definition.property.valueGenerator.impl.fixed.FixedValueGenerator;
import simulator.definition.property.valueGenerator.impl.random.impl.bool.BooleanRandomValueGenerator;
import simulator.definition.property.valueGenerator.impl.random.impl.numeric.impl.IntegerRandomValueGenerator;

public interface ValueGeneratorFactory {
    static <T> ValueGenerator<T> createFixed(T value) {
        return new FixedValueGenerator<T>(value);
    }

    static ValueGenerator<Boolean> createRandomBoolean() {
        return new BooleanRandomValueGenerator();
    }

    static ValueGenerator<Integer> createRandomInteger(Integer from, Integer to) {
        return new IntegerRandomValueGenerator(from, to);
    }

}
