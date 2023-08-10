package simulator.builder.mainBuilder.api;

import simulator.builder.componentsBuilder.api.EntityBuilder;
import simulator.builder.componentsBuilder.api.EnvironmentBuilder;
import simulator.builder.componentsBuilder.api.RuleBuilder;
import simulator.builder.componentsBuilder.api.TerminationBuilder;

public abstract class AbstractWorldBuilder implements WorldBuilder {
    private EnvironmentBuilder envBuilder;
    private EntityBuilder entityBuilder;
    private RuleBuilder ruleBuilder;
    private TerminationBuilder terminationBuilder;

    public AbstractWorldBuilder(EnvironmentBuilder envBuilder, EntityBuilder entityBuilder, RuleBuilder ruleBuilder, TerminationBuilder terminationBuilder) {
        this.envBuilder = envBuilder;
        this.entityBuilder = entityBuilder;
        this.ruleBuilder = ruleBuilder;
        this.terminationBuilder = terminationBuilder;
    }
}
