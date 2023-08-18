package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class ConditionAction extends AbstractAction {
    private ConditionExpression condition;
    private AbstractAction actionThen;
    private AbstractAction actionElse;
    public ConditionAction(eActionType type, String entityName) {
        super(type, entityName);
    }

    @Override
    public void invoke(ExecutionContext context) {

    }
}
