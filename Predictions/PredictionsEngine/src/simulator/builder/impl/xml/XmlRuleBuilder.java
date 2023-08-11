package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDRule;
import simulator.builder.api.AbstractFileComponentBuilder;
import simulator.builder.api.RuleBuilder;
import simulator.definition.rule.Rule;

public class XmlRuleBuilder extends AbstractFileComponentBuilder<Rule> implements RuleBuilder {

    private PRDRule generatedRule;

    @Override
    public Rule buildRule() {
        return null;
    }

    @Override
    public void buildActivation() {

    }

    @Override
    public void buildActions() {

    }

    public void setGeneratedRule(PRDRule generatedRule) {
        this.generatedRule = generatedRule;
    }
}
