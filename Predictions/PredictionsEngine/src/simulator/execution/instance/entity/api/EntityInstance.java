package simulator.execution.instance.entity.api;

import enums.PropertyType;
import simulator.execution.instance.property.api.PropertyInstance;
import structure.coordinate.api.Coordinate;

import java.util.Map;

public interface EntityInstance {
    PropertyInstance getPropertyInstanceByName(String propertyName);
    Map<String, PropertyInstance> getPropertiesMap();
    boolean HasProperty(String propertyName, PropertyType propertyType);
    void addPropertyInstance(String propertyName, PropertyInstance propertyInstance);
    int getId();
    void killMyself();
    boolean isAlive();
    Coordinate getCoordinate();
    void setCoordinate(Coordinate coordinate);
    String getEntityNameFamily();

}
