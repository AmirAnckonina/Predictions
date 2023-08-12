package simulator.builder.world.api;

import simulator.definition.environment.Environment;
import simulator.definition.property.api.BasePropertyDefinition;

import java.util.List;

public interface EnvironmentBuilder {
    Environment buildEnvironment();
    List<BasePropertyDefinition> buildEnvironmentProperties();
}
