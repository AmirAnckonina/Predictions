package simulator.definition.property.impl;

import simulator.definition.property.api.AbstractPropertyDefinition;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.property.valueGenerator.api.ValueGenerator;

import java.util.Optional;

public class IntegerPropertyDefinition extends AbstractPropertyDefinition<Integer> {
    // Consider add propType in c'tor.
    private final Optional<Integer> from;
    private final Optional<Integer> to;
    public IntegerPropertyDefinition(String name, ValueGenerator<Integer> valueGenerator) {
        super(name, ePropertyType.DECIMAL, valueGenerator);
        from = Optional.empty();
        to = Optional.empty();
    }

    public IntegerPropertyDefinition(String name, ePropertyType propertyType, ValueGenerator<Integer> valueGenerator, int from, int to) {
        super(name, propertyType, valueGenerator);
        this.from = Optional.of(from);
        this.to = Optional.of(to);
    }
}
