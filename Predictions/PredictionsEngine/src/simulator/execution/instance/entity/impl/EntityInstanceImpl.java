package simulator.execution.instance.entity.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;

import java.util.Map;

public class EntityInstanceImpl implements EntityInstance {
    private Map<String, PropertyInstance> properties;

    @Override
    public PropertyInstance getPropertyByName(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public void addProperty(String propertyName, PropertyInstance propertyInstance) {
        properties.put(propertyName, propertyInstance);
    }
}
