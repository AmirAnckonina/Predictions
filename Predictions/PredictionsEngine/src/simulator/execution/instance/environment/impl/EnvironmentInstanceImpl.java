package simulator.execution.instance.environment.impl;

import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.property.impl.PropertyInstanceImpl;

import java.util.Map;

public class EnvironmentInstanceImpl implements EnvironmentInstance {
    private Map<String, PropertyInstance> environmentVariables;

    public EnvironmentInstanceImpl(Map<String, PropertyInstance> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    @Override
    public PropertyInstance getPropertyByName(String name) {
        return environmentVariables.get(name);
    }

    @Override
    public void addPropertyInstance(PropertyInstance propertyInstance) {
        environmentVariables.put(propertyInstance.getPropertyDefinition().getName() ,propertyInstance);
    }
}
