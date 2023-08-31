package simulator.execution.context.impl;

import simulator.execution.context.api.ExecutionContext;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;

public class ExecutionContextImpl implements ExecutionContext {
    EntityInstance primaryEntityInstance;
    EntitiesInstancesManager entitiesInstancesManager;
    private EnvironmentInstance environmentInstance;

    public ExecutionContextImpl(EntityInstance primaryEntityInstance, EntitiesInstancesManager entitiesInstancesManager, EnvironmentInstance environmentInstance) {
        this.primaryEntityInstance = primaryEntityInstance;
        this.entitiesInstancesManager = entitiesInstancesManager;
        this.environmentInstance = environmentInstance;
    }


    @Override
    public EntityInstance getPrimaryEntityInstance() {
        return primaryEntityInstance;
    }

    @Override
    public void removeEntity(String entityName, EntityInstance entityInstance) {
        entitiesInstancesManager.killEntity(entityName,entityInstance.getId());
    }

    @Override
    public PropertyInstance getEnvironmentVariable(String name) {

        return environmentInstance.getPropertyByName(name);
    }
}
