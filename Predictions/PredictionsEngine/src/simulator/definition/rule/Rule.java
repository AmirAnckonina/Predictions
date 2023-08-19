package simulator.definition.rule;

import java.util.List;
import java.util.Optional;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.activation.Activation;

public class Rule {
    private String name;

    private List<AbstractAction> actions;

    private Activation activation;

    public Rule(String name, List<AbstractAction> actions, Activation activation) {
        this.name = name;
        this.actions = actions;
        this.activation = activation;
    }

    public Activation getActivation() {
        return activation;
    }

    public List<AbstractAction> getActions() {
        return actions;
    }

}
