package simulator.execution.context.impl;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.execution.context.api.ExecutionContext;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.world.impl.WorldInstanceImpl;


import java.util.List;

public class ExecutionContextImpl implements ExecutionContext {
    WorldInstanceImpl worldInstance;
    EnvironmentInstance environmentInstance;
    List<EntityInstance> entityInstances;


    public ExecutionContextImpl(WorldInstanceImpl worldInstance) {
        this.worldInstance = worldInstance;
        this.environmentInstance = worldInstance.getEnvironmentInstance();
        this.entityInstances = worldInstance.getPrimaryEntities();
    }

    @Override
    public Object getPropertyValueByName(String propertyName) {
        return null;
    }

    @Override
    public String getPropertyTypeByName(String methodParameter) {

        return null;
    }

    @Override
    public void removePraimerytInstance(String name) {
        worldInstance.getPrimaryEntities().remove(name);
    }

    @Override
    public void setPropertyInstanceValue(String propertyName, double value) {
        //worldInstance.getPrimaryEntities().get(propertyName);
    }

    @Override
    public ePropertyType getPropertyType(String propertyName) {
        return null;
    }
}
