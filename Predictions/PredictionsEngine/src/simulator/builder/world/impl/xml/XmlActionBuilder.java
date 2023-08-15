package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.ActionBuilder;
import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.impl.IncreaseAction;
import simulator.definition.rule.action.utils.eActionType;

import java.util.List;


public class XmlActionBuilder implements ActionBuilder {
    PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction) {
        this.generatedAction = generatedAction;
    }


    @Override
    public AbstractAction BuildAction() {
        eActionType actionType = eActionType.valueOf(generatedAction.getType());

        return null;
    }

}
