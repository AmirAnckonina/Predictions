package simulator.builder.componentsBuilder.api;

import definition.environment.Environment;

public interface EnvironmentBuilder {
    Environment getEnvironment();
    void buildEnvironment();
    void buildEnvironmentProperty();
}
