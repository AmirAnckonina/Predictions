package simulator.execution.instance.environment;

import simulator.execution.context.api.EnvironmentInstance;
import simulator.execution.context.api.PropertyInstance;
import simulator.execution.instance.property.PropertyInstanceImpl;

import java.util.Map;

public class EnvironmentInstanceImpl implements EnvironmentInstance {
    private Map<String, PropertyInstance> environmentVariables;

    public EnvironmentInstanceImpl() {

    }

    @Override
    public PropertyInstance getProperty(String name) {
        return null;
    }

    @Override
    public void addPropertyInstance(PropertyInstance propertyInstance) {

    }
}
