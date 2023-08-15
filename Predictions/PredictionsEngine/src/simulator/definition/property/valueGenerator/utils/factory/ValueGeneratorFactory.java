package simulator.definition.property.valueGenerator.utils.factory;

import simulator.definition.property.impl.Range;
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
    static ValueGenerator<Integer> createRandomRangedIntegerGenerator(Range range) {
        return new IntegerRandomValueGenerator(range);
    }
    static ValueGenerator<Float> createRandomRangedFloatGenerator(Range range) {
        return new FloatRandomValueGenerator(range);
    }
    static ValueGenerator<Integer> createRandomUnlimitedIntegerGenerator() {
        return new IntegerRandomValueGenerator();
    }
    static ValueGenerator<Float> createRandomUnlimitedFloatGenerator() {
        return new FloatRandomValueGenerator();
    }

}
