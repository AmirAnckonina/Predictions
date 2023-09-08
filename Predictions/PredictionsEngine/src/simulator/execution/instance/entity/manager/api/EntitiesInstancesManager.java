package simulator.execution.instance.entity.manager.api;

import simulator.definition.entity.EntityDefinition;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.execution.instance.entity.api.EntityInstance;

import java.util.List;

public interface EntitiesInstancesManager {
    EntityInstance createEntityInstance(EntityDefinition entityDefinition);
    List<EntityInstance> getEntityInstances(String entityName);
    void killEntity(String entityName, int id);

}
