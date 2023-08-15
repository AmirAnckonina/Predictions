package simulator.builder.world.impl;

import simulator.builder.world.api.ActionBuilder;
import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.utils.eActionType;

public class BaseActionBuilder implements ActionBuilder {

    @Override
    public AbstractAction BuildAction(eActionType actionType) {
        return null;
    }
}
