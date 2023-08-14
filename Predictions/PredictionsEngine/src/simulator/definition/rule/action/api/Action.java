package simulator.definition.rule.action.api;

import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.Context;

public interface Action {
    void invoke(Context context);
    eActionType getType();
    String getEntityName();
}
