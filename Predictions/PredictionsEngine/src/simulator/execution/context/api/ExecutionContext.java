package simulator.execution.context.api;

public interface ExecutionContext {
    Object getPropertyValueByName(String propertyName);

    String getPropertyTypeByName(String methodParameter);


    void removePraimertInstance(String name);

    void setPropertyInstanceValue(String propertyName, double value);
}
