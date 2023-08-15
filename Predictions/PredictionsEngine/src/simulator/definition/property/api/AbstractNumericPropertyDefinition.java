package simulator.definition.property.api;

import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.impl.Range;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

public class AbstractNumericPropertyDefinition<T extends Number> extends AbstractPropertyDefinition<T> {

    private boolean hasRange;
    private Range<T> range;
    public AbstractNumericPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<T> valueGenerator) {
        super(name, propertyType, valueGenerator);
        this.hasRange = false;
        this.range = null;
    }

    public AbstractNumericPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<T> valueGenerator, boolean hasRange, Range<T> range) {
        super(name, propertyType, valueGenerator);
        this.hasRange = hasRange;
        this.range = range;
    }

    public void setRange(Range<T> range) {
        this.range = range;
        this.hasRange = true;
    }
}
