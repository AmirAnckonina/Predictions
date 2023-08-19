package simulator.execution.context.impl;

import simulator.execution.context.api.ExecutionContext;
import simulator.execution.instance.world.impl.WorldInstanceImpl;

public class ExecutionContextImpl implements ExecutionContext {
    WorldInstanceImpl worldInstance;

    public ExecutionContextImpl(WorldInstanceImpl worldInstance) {
        this.worldInstance = worldInstance;
    }

    @Override
    public String getPropertyValueByName(String propertyName) {
        return null;
    }

    @Override
    public String getPropertyTypeByName(String methodParameter) {
        return null;
    }

    @Override
    public void removePraimertInstance(String name) {
        worldInstance.getPrimaryEntities().remove(name);
    }

    @Override
    public void setPropertyInstanceValue(String propertyName, double value) {
        //worldInstance.getPrimaryEntities().get(propertyName);
    }
}
