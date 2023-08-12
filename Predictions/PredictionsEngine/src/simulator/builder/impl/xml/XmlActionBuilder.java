package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.api.AbstractFileComponentBuilder;
import simulator.builder.api.ActionBuilder;
import simulator.definition.rule.action.Action;

public class XmlActionBuilder extends AbstractFileComponentBuilder<Action> implements ActionBuilder {
    PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction) {
        this.generatedAction = generatedAction;
    }


    @Override
    public Action BuildAction() {
        //generatedAction
        return null;
    }
}
