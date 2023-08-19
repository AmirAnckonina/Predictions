package simulator.execution.instance.environment.api;

import simulator.execution.instance.property.api.PropertyInstance;

public interface EnvironmentInstance {
    PropertyInstance getProperty(String name);
    void addPropertyInstance(PropertyInstance propertyInstance);
}
