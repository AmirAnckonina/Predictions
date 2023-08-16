package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.world.api.AbstractComponentBuilder;
import simulator.builder.world.api.ActionBuilder;
import simulator.builder.world.validator.api.WorldContextBuilderHelper;
import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.utils.eActionType;


public class XmlActionBuilder extends AbstractComponentBuilder implements ActionBuilder {
    PRDAction generatedAction;

    public XmlActionBuilder(PRDAction generatedAction, WorldContextBuilderHelper contextValidator) {
        super(contextValidator);
        this.generatedAction = generatedAction;
    }


    @Override
    public AbstractAction BuildAction() {
        eActionType actionType = eActionType.valueOf(generatedAction.getType());

        return null;
    }

}
