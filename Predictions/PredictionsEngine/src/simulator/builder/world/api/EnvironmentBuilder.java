package simulator.builder.world.api;

import simulator.definition.environment.Environment;
import simulator.definition.property.api.AbstractPropertyDefinition;

import java.util.Map;

public interface EnvironmentBuilder {
    Environment buildEnvironment();
    Map<String, AbstractPropertyDefinition> buildEnvironmentProperties();
}
