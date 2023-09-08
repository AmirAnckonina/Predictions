package simulator.execution.instance.entity.manager.impl;

import simulator.definition.entity.EntityDefinition;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.SecondaryEntitySelectionType;
import simulator.execution.context.impl.ExecutionContextImpl;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.manager.api.EntitiesInstancesManager;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
