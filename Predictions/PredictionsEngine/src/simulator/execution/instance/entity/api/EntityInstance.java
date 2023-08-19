package simulator.execution.instance.entity.api;

import simulator.execution.instance.property.api.PropertyInstance;

public interface EntityInstance {
    PropertyInstance getPropertyByName(String propertyName);
    void addProperty(String propertyName, PropertyInstance propertyInstance);
}
