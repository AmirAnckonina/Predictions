package simulator.definition.property.impl;

import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.Optional;

public class FloatPropertyDefinition extends AbstractPropertyDefinition<Float> {
    // Consider add propType in c'tor.
    private final Optional<Float> from;
    private final Optional<Float> to;
    public FloatPropertyDefinition(String name, ValueGenerator<Float> valueGenerator) {
        super(name, ePropertyType.FLOAT, valueGenerator);
        this.from = Optional.empty();
        this.to = Optional.empty();
    }

    public FloatPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Float> valueGenerator, float from, float to) {
        super(name, propertyType, valueGenerator);
        this.from = Optional.of(from);
        this.to = Optional.of(to);
    }


    public Optional<Float> getFrom() {
        return from;
    }

    public Optional<Float> getTo() {
        return to;
    }
}
