package simulator.execution.instance.entity.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;

import java.util.Map;

public class EntityInstanceImpl implements EntityInstance {

    private int id;
    private Map<String, PropertyInstance> properties;

    public EntityInstanceImpl(int id, Map<String, PropertyInstance> properties) {
        this.id = id;
        this.properties = properties;
    }

    @Override
    public PropertyInstance getPropertyByName(String propertyName) {

        return properties.get(propertyName);
    }

    @Override
    public void addPropertyInstance(String propertyName, PropertyInstance propertyInstance) {
        properties.put(propertyName, propertyInstance);
    }

    @Override
    public int getId() {
        return id;
    }
}
