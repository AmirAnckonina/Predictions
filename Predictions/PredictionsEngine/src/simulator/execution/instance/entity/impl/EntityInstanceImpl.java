package simulator.execution.instance.entity.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import structure.api.Coordinate;

import java.util.Map;

public class EntityInstanceImpl implements EntityInstance {
    private String entityNameFamily;
    private boolean alive;
    private int id;
    private Map<String, PropertyInstance> properties;
    private Coordinate coordinate;

    public EntityInstanceImpl(String entityNameFamily, int id, Map<String, PropertyInstance> properties) {
        this.entityNameFamily = entityNameFamily;
        this.id = id;
        this.alive = true;
        this.properties = properties;
    }

    @Override
    public PropertyInstance getPropertyInstanceByName(String propertyName) {

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

    @Override
    public void killEntity() {
        alive = false;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
  
    @Override
    public String getEntityNameFamily() {
        return this.entityNameFamily;

    }
}
