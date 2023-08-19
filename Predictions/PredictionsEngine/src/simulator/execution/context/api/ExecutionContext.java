package simulator.execution.context.api;

import simulator.execution.instance.entity.api.EntityInstance;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;

import java.util.List;


public interface ExecutionContext {

    EntityInstance getPrimaryEntityInstance();
    void removeEntity(EntityInstance entityInstance);
    PropertyInstance getEnvironmentVariable(String name);
}
