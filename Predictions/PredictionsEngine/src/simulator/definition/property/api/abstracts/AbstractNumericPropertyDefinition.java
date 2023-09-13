package simulator.definition.property.api.abstracts;

import enums.PropertyType;
import simulator.definition.property.impl.Range;
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
