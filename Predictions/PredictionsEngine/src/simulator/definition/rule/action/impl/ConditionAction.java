package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class ConditionAction extends AbstractAction {
    public ConditionAction(eActionType type, String entityName) {
        super(type, entityName);
    }

    @Override
    public void invoke(ExecutionContext context) {

    }
}
