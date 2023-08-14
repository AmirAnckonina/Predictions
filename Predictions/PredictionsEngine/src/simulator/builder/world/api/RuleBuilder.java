package simulator.builder.world.api;

import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.activation.Activation;

import java.util.List;

public interface RuleBuilder {
    Rule buildRule();
    Activation buildActivation();
    List<AbstractAction> buildActions();
}
