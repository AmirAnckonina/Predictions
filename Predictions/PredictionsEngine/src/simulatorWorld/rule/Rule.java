package simulatorWorld.rule;

import java.util.ArrayList;
import java.util.List;
import simulatorWorld.rule.action.Action;
import simulatorWorld.rule.activation.Activation;

public class Rule {
    private List<Action> actions;
    private Activation activation;
    public Rule() {
        actions = new ArrayList<>();
        activation = new Activation();
    }
}
