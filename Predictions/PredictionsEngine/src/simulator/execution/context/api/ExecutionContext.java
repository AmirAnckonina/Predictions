package simulator.execution.context.api;

import simulator.definition.property.utils.enums.ePropertyType;

public interface ExecutionContext {
    Object getPropertyValueByName(String propertyName);

    String getPropertyTypeByName(String methodParameter);

    void removePraimerytInstance(String name);

    void setPropertyInstanceValue(String propertyName, double value);

    ePropertyType getPropertyType(String propertyName);
}
