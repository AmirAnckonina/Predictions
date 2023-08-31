package simulator.execution.instance.entity.manager.api;

import simulator.definition.entity.EntityDefinition;
import simulator.execution.instance.entity.api.EntityInstance;

import java.util.List;

public interface EntityInstanceManager {
    EntityInstance create(EntityDefinition entityDefinition);
    List<EntityInstance> getInstances();
    void killEntity(int id);
}
