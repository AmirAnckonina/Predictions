package simulator.execution.context.api;

import simulator.execution.instance.entity.api.EntityInstance;

import simulator.execution.instance.property.api.PropertyInstance;


public interface ExecutionContext {

    EntityInstance getEntityInstanceByName(String entityName);
    void removeEntity(String entityName, EntityInstance entityInstance);
    PropertyInstance getEnvironmentVariable(String name);
    void addEntityInstance(EntityInstance additionalEntityInstance);
}
