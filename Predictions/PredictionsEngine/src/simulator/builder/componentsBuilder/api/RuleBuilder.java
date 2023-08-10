package simulator.builder.componentsBuilder.api;

import simulator.definition.rule.Rule;

public interface RuleBuilder {
    Rule buildRule();
    void buildActivation();
    void buildActions();
}
