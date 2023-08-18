package simulator.execution.instance.entity;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.execution.context.api.EntityInstance;
import simulator.execution.context.api.PropertyInstance;

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
