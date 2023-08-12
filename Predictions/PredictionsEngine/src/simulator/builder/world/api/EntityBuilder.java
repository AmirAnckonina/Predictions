package simulator.builder.world.api;

import simulator.definition.entity.Entity;

public interface EntityBuilder {

    Entity buildEntity();
    void buildEntityProperty();
}
