package simulator.builder.api.interfaces;

import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.Map;

public interface EntityBuilder {

    EntityDefinition buildEntity();
    Map<String,AbstractPropertyDefinition> buildEntityProperties();
}
