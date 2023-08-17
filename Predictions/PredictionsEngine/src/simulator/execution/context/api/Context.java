package simulator.execution.context.api;

public interface Context {
    String getPropertyValueByName(String propertyName);

    String getPropertyTypeByName(String methodParameter);
}
