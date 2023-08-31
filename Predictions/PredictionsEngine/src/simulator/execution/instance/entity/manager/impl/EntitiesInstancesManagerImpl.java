package simulator.execution.instance.entity.manager.impl;

import simulator.definition.entity.EntityDefinition;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.List;
import java.util.Map;

public class EntitiesInstancesManagerImpl implements EntitiesInstancesManager {

    Map<String, List<EntityInstance>> entitiesInstances;

    public EntitiesInstancesManagerImpl(Map<String, List<EntityInstance>> entitiesInstances) {
        this.entitiesInstances = entitiesInstances;
    }


    @Override
    public EntityInstance createEntityInstance(EntityDefinition entityDefinition) {
        throw new SimulatorRunnerException("not implemented createEntityInstance method");
    }

    @Override
    public List<EntityInstance> getEntityInstances(String entityName) {
        return entitiesInstances.get(entityName);
    }

    @Override
    public void killEntity(String entityName, int id) {

        entitiesInstances
                .get(entityName)
                .removeIf(
                        entIns -> entIns.getId() == id
                );
    }
}
