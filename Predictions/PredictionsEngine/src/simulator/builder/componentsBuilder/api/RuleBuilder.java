package simulator.builder.componentsBuilder.api;

import definition.rule.Rule;

public interface RuleBuilder {
    Rule getRule();
    void buildRule();
    void buildActivation();
    void buildActions();
}
