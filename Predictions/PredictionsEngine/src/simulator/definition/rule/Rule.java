package simulator.definition.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.activation.Activation;

public class Rule {
    private String name;
    private List<AbstractAction> actions;
    private Optional<Activation> activation;

    public Rule(String name, List<AbstractAction> actions, Activation activation) {
        this.name = name;
        this.actions = actions;
        this.activation = Optional.ofNullable(activation);
    }
    public Rule(String name, List<AbstractAction> actions) {
        this.name = name;
        this.actions = actions;
        this.activation = Optional.empty();
    }
}
