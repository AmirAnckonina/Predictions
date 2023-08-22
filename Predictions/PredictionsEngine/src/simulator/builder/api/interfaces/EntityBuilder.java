package simulator.builder.api.interfaces;

import simulator.definition.entity.Entity;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.Map;

public interface EntityBuilder {

    Entity buildEntity();
    Map<String,AbstractPropertyDefinition> buildEntityProperties();
}
