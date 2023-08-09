package definition.rule;

import java.util.ArrayList;
import java.util.List;
import definition.rule.action.Action;
import definition.rule.activation.Activation;

public class Rule {
    private List<Action> actions;
    private Activation activation;
    public Rule() {
        actions = new ArrayList<>();
        activation = new Activation();
    }
}
