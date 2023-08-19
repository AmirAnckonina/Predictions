package simulator.execution.context.api;

import simulator.execution.instance.entity.api.EntityInstance;

public interface ExecutionContext {

    Object getPropertyValueByName(String propertyName);

    String getPropertyTypeByName(String methodParameter);


    void removePraimertInstance(String name);

    void setPropertyInstanceValue(String propertyName, double value);
}
