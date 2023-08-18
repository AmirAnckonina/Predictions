package simulator.execution.context.api;

public interface EnvironmentInstance {
    PropertyInstance getProperty(String name);
    void addPropertyInstance(PropertyInstance propertyInstance);
}
