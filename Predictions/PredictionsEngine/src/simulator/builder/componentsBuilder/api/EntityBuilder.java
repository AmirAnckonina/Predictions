package simulator.builder.componentsBuilder.api;

import definition.entity.Entity;

public interface EntityBuilder {
    Entity getEntity();
    void buildEntity();
    void buildEntityProperty();
}
