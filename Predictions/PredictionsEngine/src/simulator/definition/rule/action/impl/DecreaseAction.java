package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractPropertyAction;
import simulator.definition.rule.action.expression.api.Expression;
import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class DecreaseAction extends AbstractPropertyAction {
    Expression by;

    public DecreaseAction(eActionType type, String entityName, String propertyName, Expression by) {
        super(type, entityName, propertyName);
        this.by = by;
    }

    @Override
    public void invoke(ExecutionContext context) {

    }
}
