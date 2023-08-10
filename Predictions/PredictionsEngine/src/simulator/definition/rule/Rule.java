package simulator.definition.rule;

import java.util.ArrayList;
import java.util.List;
import simulator.definition.rule.action.Action;
import simulator.definition.rule.activation.Activation;
import simulator.definition.world.World;

public class Rule {
    private List<Action> actions;
    private Activation activation;
    private boolean wasExecuted;    // Indicate if the rule already activated once - need this?
    public Rule() {
        actions = new ArrayList<>();
        activation = new Activation();
    }


    /**
     * Precede 1 tick: activate any activations needed to be Execute
     * @param currTick - get timeline index
     */
    public boolean PrecedeTick(Integer currTick){
        return true;

    }
    public boolean PrecedeTick(World world){
        return true;
    }
}
