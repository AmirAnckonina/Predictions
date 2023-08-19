package simulator.execution.instance.environment.impl;

import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.property.impl.PropertyInstanceImpl;

import java.util.Map;

public class EnvironmentInstanceImpl implements EnvironmentInstance {
    private Map<String, PropertyInstanceImpl> environmentVariables;

    @Override
    public PropertyInstance getProperty(String name) {
        return null;
    }

    @Override
    public void addPropertyInstance(PropertyInstance propertyInstance) {

    }
}
