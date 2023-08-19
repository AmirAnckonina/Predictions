package simulator.execution.instance.entity.api;

import simulator.execution.instance.property.api.PropertyInstance;

public interface EntityInstance {
    PropertyInstance getPropertyByName(String propertyName);
    void addPropertyInstance(String propertyName, PropertyInstance propertyInstance);
    int getId();
}
