package simulator.builder.api.interfaces;

import simulator.definition.environment.Environment;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.Map;

public interface EnvironmentBuilder {
    Environment buildEnvironment();
    Map<String, AbstractPropertyDefinition> buildEnvironmentProperties();
}
