package simulator.builder.api;

import simulator.definition.property.api.BasePropertyDefinition;

public interface PropertyBuilder {
    <T> BasePropertyDefinition<T> buildEnvironmentProperty();
    <T> BasePropertyDefinition<T> buildEntityProperty();
}
