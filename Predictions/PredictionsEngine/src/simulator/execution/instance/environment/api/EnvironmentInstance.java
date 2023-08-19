package simulator.execution.instance.environment.api;

import simulator.execution.instance.property.api.PropertyInstance;

import java.util.Map;

public interface EnvironmentInstance {
    PropertyInstance getPropertyByName(String name);

    Map<String, PropertyInstance> getAllEnvironmentPropertiesInstances();

    void addPropertyInstance(PropertyInstance propertyInstance);
}
