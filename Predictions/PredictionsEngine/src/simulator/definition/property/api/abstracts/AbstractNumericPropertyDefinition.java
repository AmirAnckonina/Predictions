package simulator.definition.property.api.abstracts;

import enums.PropertyType;
import simulator.definition.property.valueGenerator.utils.exception.ValueGeneratorSetupException;
import structure.range.Range;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.Optional;

public class AbstractNumericPropertyDefinition<T extends Number> extends AbstractPropertyDefinition<T> {
    private Range<T> range;

    public AbstractNumericPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<T> valueGenerator, Range<T> range) {
        super(name, propertyType, valueGenerator);
        this.range = range;
    }

    public AbstractNumericPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<T> valueGenerator) {
        this(name, propertyType, valueGenerator, null);
    }

    public Optional<Range<T>> getRange() {
        return Optional.ofNullable(range);
    }

    @Override
    public void setFixedValueGenerator(T fixedValue) {
        getRange().ifPresent((rng) -> {
            if (!rng.inRangeValidation(fixedValue)) {
                throw new ValueGeneratorSetupException(
                        String.format(
                                "The given fixed value %d is out of the range %d - %d", fixedValue, rng.getFrom(), rng.getTo()
                        )
                );
            }
        });
        super.setFixedValueGenerator(fixedValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (getRange().isPresent()) {
            sb.append(range.toString());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
