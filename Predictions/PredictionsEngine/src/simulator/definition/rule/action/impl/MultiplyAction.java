package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.ActionType;
import simulator.definition.rule.action.api.abstracts.AbstractCalculationAction;
import simulator.execution.context.api.ExecutionContext;

public class MultiplyAction extends AbstractCalculationAction {

    public MultiplyAction(ActionType type, String entityName, String propertyName, ArgumentExpression argumentExpression1, ArgumentExpression argumentExpression2) {
        super(type, entityName, propertyName, argumentExpression1, argumentExpression2);
    }

    @Override

    public void invoke(ExecutionContext context) {
        context.getPrimaryEntityInstance().getPropertyByName(propertyName).updateValue((double)arg1.getValue(context) * (double)arg2.getValue(context));
    }
}
