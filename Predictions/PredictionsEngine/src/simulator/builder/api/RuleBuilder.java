package simulator.builder.api;

import simulator.definition.rule.Rule;
import simulator.definition.rule.action.Action;
import simulator.definition.rule.activation.Activation;

import java.util.List;

public interface RuleBuilder {
    Rule buildRule();
    Activation buildActivation();
    List<Action> buildActions();
}
