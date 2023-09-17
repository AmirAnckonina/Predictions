package simulator.execution.instance.entity.impl;

import simulator.definition.entity.impl.EntityDefinition;

import java.util.Set;

public class EntityInstancesImpl {

    Set<EntityDefinition> entities;

    public EntityInstancesImpl(Set<EntityDefinition> entities) {
        this.entities = entities;
    }

    public Set<EntityDefinition> getEntities() {
        return entities;
    }

    public void setEntities(Set<EntityDefinition> entities) {
        this.entities = entities;
    }
}
