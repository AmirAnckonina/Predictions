package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class KillAction extends AbstractAction {
    public KillAction(eActionType type, String entityName) {
        super(type, entityName);
    }

    @Override
    public void invoke(ExecutionContext executionContext) {
        executionContext.removeEntity(executionContext.getPrimaryEntityInstance());
    }
}
