package simulator.definition.rule.action.api;

import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.ExecutionContext;

public interface Action {
    void invoke(ExecutionContext context);
    eActionType getType();
    String getEntityName();
}
