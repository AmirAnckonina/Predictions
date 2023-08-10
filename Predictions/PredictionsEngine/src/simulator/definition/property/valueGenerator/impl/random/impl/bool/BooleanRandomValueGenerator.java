package simulator.definition.property.valueGenerator.impl.random.impl.bool;

import simulator.definition.property.valueGenerator.impl.random.api.BaseRandomValueGenerator;

public class BooleanRandomValueGenerator extends BaseRandomValueGenerator<Boolean> {

    @Override
    public Boolean generateValue() {
        return random.nextDouble() < 0.5;
    }
}
