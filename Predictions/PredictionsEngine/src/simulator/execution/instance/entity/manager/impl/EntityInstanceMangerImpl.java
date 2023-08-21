package simulator.execution.instance.entity.manager.impl;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.execution.instance.property.api.PropertyInstance;

import java.util.List;

public class EntityInstanceMangerImpl implements EntityInstanceManager {
    private List<EntityInstance> entityInstances;

    public EntityInstanceMangerImpl(List<EntityInstance> entityInstances) {

        this.entityInstances = entityInstances;
    }

    @Override
    public List<EntityInstance> getInstances() {
        return entityInstances;
    }

    @Override
    public void killEntity(int id) {
        entityInstances.removeIf(entIns -> entIns.getId() == id);
    }
}
