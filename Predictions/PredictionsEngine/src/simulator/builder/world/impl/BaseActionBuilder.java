package simulator.builder.world.impl;

import simulator.builder.world.api.AbstractComponentBuilder;
import simulator.builder.world.api.ActionBuilder;
import simulator.builder.world.validator.api.WorldContextBuilderHelper;
import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.utils.eActionType;

public class BaseActionBuilder extends AbstractComponentBuilder implements ActionBuilder {

    public BaseActionBuilder(WorldContextBuilderHelper contextValidator) {
        super(contextValidator);
    }

    @Override
    public AbstractAction BuildAction(eActionType actionType) {
        return null;
    }
}
