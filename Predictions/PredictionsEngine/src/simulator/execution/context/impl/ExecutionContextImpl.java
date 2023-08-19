package simulator.execution.context.impl;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.execution.context.api.ExecutionContext;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.instance.world.impl.WorldInstanceImpl;


import java.util.List;

public class ExecutionContextImpl implements ExecutionContext {
    EntityInstance primaryEntityInstance;
    EntityInstanceManager entityInstanceManager;
    private EnvironmentInstance environmentInstance;

    public ExecutionContextImpl(EntityInstance primaryEntityInstance, EntityInstanceManager entityInstanceManager, EnvironmentInstance environmentInstance) {
        this.primaryEntityInstance = primaryEntityInstance;
        this.entityInstanceManager = entityInstanceManager;
        this.environmentInstance = environmentInstance;
    }


    @Override
    public EntityInstance getPrimaryEntityInstance() {
        return primaryEntityInstance;
    }

    @Override
    public void removeEntity(EntityInstance entityInstance) {
        entityInstanceManager.killEntity(entityInstance.getId());
    }

    @Override
    public PropertyInstance getEnvironmentVariable(String name) {

        return environmentInstance.getProperty(name);
    }
}
