package simulator.builder.world.api;

import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.definition.property.api.AbstractPropertyDefinition;

import java.util.Map;

public interface PropertyBuilder {
    AbstractPropertyDefinition buildEntityProperty();
    AbstractPropertyDefinition buildEnvironmentProperty();
}
