package simulator.execution.instance.entity.impl;

import enums.PropertyType;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import structure.coordinate.api.Coordinate;

import java.util.Map;

public class EntityInstanceImpl implements EntityInstance {
    private String entityNameFamily;
    private boolean alive;
    private int id;
    private Map<String, PropertyInstance> propertiesMap;
    private Coordinate coordinate;

    public EntityInstanceImpl(String entityNameFamily, int id, Map<String, PropertyInstance> propertiesMap) {
        this.entityNameFamily = entityNameFamily;
        this.id = id;
        this.alive = true;
        this.propertiesMap = propertiesMap;
    }

    @Override
    public PropertyInstance getPropertyInstanceByName(String propertyName) {
        return propertiesMap.get(propertyName);
    }

    @Override
    public Map<String, PropertyInstance> getPropertiesMap() {
        return this.propertiesMap;
    }

    @Override
    public boolean HasProperty(String propertyName, PropertyType propertyType) {
        return this.propertiesMap.containsKey(propertyName)
                && this.propertiesMap.get(propertyName).getPropertyDefinition().getType() == propertyType;
    }

    @Override
    public void addPropertyInstance(String propertyName, PropertyInstance propertyInstance) {
        propertiesMap.put(propertyName, propertyInstance);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void killMyself() {
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
