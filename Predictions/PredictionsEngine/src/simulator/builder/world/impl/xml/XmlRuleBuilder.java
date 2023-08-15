package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import resources.jaxb.schema.generated.PRDRule;
import resources.jaxb.schema.generated.PRDRules;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.RuleBuilder;
import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.api.Action;
import simulator.definition.rule.activation.Activation;

import java.util.ArrayList;
import java.util.List;

public class XmlRuleBuilder extends AbstractFileComponentBuilder implements RuleBuilder {

    private final PRDRule generatedRule;

    public XmlRuleBuilder(PRDRule generatedRule) {

        this.generatedRule = generatedRule;
    }

    @Override
    public Rule buildRule() {
        String name = generatedRule.getName();
        List<AbstractAction> actions = buildActions();
        Activation activation = buildActivation();
        return new Rule(name, actions, activation);
    }

    @Override
    public Activation buildActivation() {
        return null;
    }

    @Override
    public List<AbstractAction> buildActions() {
        List<AbstractAction> actions = new ArrayList<>();
        for (PRDAction genAction: generatedRule.getPRDActions().getPRDAction()) {
            AbstractAction newAction = new XmlActionBuilder(genAction).BuildAction();
            actions.add(newAction);
        }
        return null;

    }
}
