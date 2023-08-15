package simulator.builder.world.api;

import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.utils.eActionType;

import java.util.List;


public interface ActionBuilder {
    AbstractAction BuildAction(eActionType actionType);
}
