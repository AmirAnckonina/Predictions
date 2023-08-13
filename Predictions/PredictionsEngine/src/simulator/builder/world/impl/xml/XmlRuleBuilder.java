package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDRule;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.RuleBuilder;
import simulator.definition.rule.Rule;
import simulator.definition.rule.activation.Activation;

import java.util.ArrayList;
import java.util.List;

public class XmlRuleBuilder extends AbstractFileComponentBuilder<Rule> implements RuleBuilder {

    private PRDRule generatedRule;

    public XmlRuleBuilder(PRDRule generatedRule) {
        this.generatedRule = generatedRule;
    }

    @Override
    public Rule buildRule() {
        String name = generatedRule.getName();
        List<Action> actions = buildActions();
        Activation activation = buildActivation();
        return new Rule(name, actions, activation);
    }

    @Override
    public Activation buildActivation() {
        return null;
    }

    @Override
    public List<Action> buildActions() {
        List<Action> actions = new ArrayList<>();

        return null;

    }

    public void setGeneratedRule(PRDRule generatedRule) {
        this.generatedRule = generatedRule;
    }
}
