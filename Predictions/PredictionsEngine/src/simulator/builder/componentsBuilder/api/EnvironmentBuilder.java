package simulator.builder.componentsBuilder.api;

import simulator.definition.environment.Environment;

public interface EnvironmentBuilder {
    Environment buildEnvironment();
    void buildEnvironmentProperty();
}
