package simulator.execution.instance.entity.impl;

import simulator.definition.entity.Entity;

import java.util.Set;

public class EntityInstancesImpl {

    Set<Entity> entities;

    public EntityInstancesImpl(Set<Entity> entities) {
        this.entities = entities;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }
}
