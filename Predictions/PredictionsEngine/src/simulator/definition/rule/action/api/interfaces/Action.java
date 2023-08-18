package simulator.definition.rule.action.api.interfaces;

import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

public interface Action {
    void invoke(ExecutionContext context);
    eActionType getType();
    String getEntityName();
}
