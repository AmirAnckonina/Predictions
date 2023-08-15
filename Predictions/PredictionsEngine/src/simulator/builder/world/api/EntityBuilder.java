package simulator.builder.world.api;

import simulator.definition.entity.Entity;
import simulator.definition.property.api.AbstractPropertyDefinition;

import java.util.Map;

public interface EntityBuilder {

    Entity buildEntity();

    Map<String,AbstractPropertyDefinition> buildEntityProperties();
}
