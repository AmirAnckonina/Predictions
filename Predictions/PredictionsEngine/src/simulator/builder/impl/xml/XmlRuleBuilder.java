package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDRule;
import simulator.builder.api.interfaces.RuleBuilder;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.definition.rule.Rule;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.activation.Activation;

import java.util.List;

public class XmlRuleBuilder extends AbstractComponentBuilder implements RuleBuilder {

    private final PRDRule generatedRule;

    public XmlRuleBuilder(PRDRule generatedRule, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
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

        Activation activation = null;
        if (generatedRule.getPRDActivation() != null) {
            Integer ticksInterval = generatedRule.getPRDActivation().getTicks();
            Double probability = generatedRule.getPRDActivation().getProbability();
            activation = new Activation(ticksInterval, probability);
        } else {
            activation = new Activation();
        }

        return activation;
    }

    @Override
    public List<AbstractAction> buildActions() {
        return new XmlActionListBuilder(
                        generatedRule.getPRDActions().getPRDAction(), contextValidator)
                        .buildActions();
    }
}
