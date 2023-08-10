package simulator.definition.rule;

import java.util.ArrayList;
import java.util.List;
import simulator.definition.rule.action.Action;
import simulator.definition.rule.activation.Activation;

public class Rule {
    private List<Action> actions;
    private Activation activation;
    public Rule() {
        actions = new ArrayList<>();
        activation = new Activation();
    }
}
