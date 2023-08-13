package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.ActionBuilder;
import simulator.definition.rule.action.api.AbstractAction;


public class XmlActionBuilder extends AbstractFileComponentBuilder<AbstractAction> implements ActionBuilder {
    PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction) {
        this.generatedAction = generatedAction;
    }


    @Override
    public AbstractAction BuildAction() {
        //generatedAction
        return null;
    }
}
