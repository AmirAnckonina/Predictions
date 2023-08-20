package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractPropertyAction;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class DecreaseAction extends AbstractPropertyAction {
    ArgumentExpression by;

    public DecreaseAction(eActionType type, String entityName, String propertyName, ArgumentExpression by) {
        super(type, entityName, propertyName);
        this.by = by;
    }

    @Override
    public void invoke(ExecutionContext executionContext) {

    }
}
