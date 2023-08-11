package simulator.builder.api;

import simulator.definition.environment.Environment;

public interface EnvironmentBuilder {
    Environment buildEnvironment();
    void buildEnvironmentProperty();
}
