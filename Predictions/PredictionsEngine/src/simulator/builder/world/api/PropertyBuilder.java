package simulator.builder.world.api;

import simulator.definition.property.api.BasePropertyDefinition;

public interface PropertyBuilder {
    <T> BasePropertyDefinition<T> buildEnvironmentProperty();
    <T> BasePropertyDefinition<T> buildEntityProperty();
}
