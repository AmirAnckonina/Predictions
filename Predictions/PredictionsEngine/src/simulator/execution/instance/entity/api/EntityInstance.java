package simulator.execution.instance.entity.api;

import simulator.execution.instance.property.api.PropertyInstance;
import structure.api.Coordinate;

public interface EntityInstance {
    PropertyInstance getPropertyInstanceByName(String propertyName);
    void addPropertyInstance(String propertyName, PropertyInstance propertyInstance);
    int getId();
    void killEntity();
    boolean isAlive();
    Coordinate getCoordinate();
    void setCoordinate(Coordinate coordinate);
    String getEntityNameFamily();

}
