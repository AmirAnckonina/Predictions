package simulator.builder.api.interfaces;

import simulator.definition.environment.EnvironmentDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.Map;

public interface EnvironmentBuilder {
    EnvironmentDefinition buildEnvironment();
    Map<String, AbstractPropertyDefinition> buildEnvironmentProperties();
}
