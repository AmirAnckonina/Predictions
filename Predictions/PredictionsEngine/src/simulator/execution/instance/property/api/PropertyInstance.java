package simulator.execution.instance.property.api;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

public interface PropertyInstance {
    AbstractPropertyDefinition getPropertyDefinition();
    Object getValue();
    void updateValue(Object val, int currTick);
    Integer getLastTickUpdate();

    Integer getNumOfUpdates();
}
