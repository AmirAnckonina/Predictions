package simulator.definition.rule;

import java.util.ArrayList;
import java.util.List;
import simulator.definition.rule.action.Action;
import simulator.definition.rule.activation.Activation;
import simulator.definition.world.World;

public class Rule {
    private String name;
    private List<Action> actions;
    private Activation activation;

    public Rule() {
        actions = new ArrayList<>();
        activation = new Activation();
    }

    public Rule(String name, List<Action> actions, Activation activation) {
        this.name = name;
        this.actions = actions;
        this.activation = activation;
    }
}
