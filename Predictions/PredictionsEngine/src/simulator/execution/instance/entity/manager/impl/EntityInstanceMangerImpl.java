package simulator.execution.instance.entity.manager.impl;

import simulator.definition.entity.impl.EntityDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntityInstanceManager;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.List;

public class EntityInstanceMangerImpl implements EntityInstanceManager {
    private List<EntityInstance> entityInstances;

    public EntityInstanceMangerImpl(List<EntityInstance> entityInstances) {

        this.entityInstances = entityInstances;
    }

    @Override
    public EntityInstance create(EntityDefinition entityDefinition) {
        throw new SimulatorRunnerException("Not implempnted create under EntityMAnagerImpl");
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
