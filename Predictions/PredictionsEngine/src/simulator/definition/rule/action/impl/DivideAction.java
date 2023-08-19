package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractCalculationAction;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class DivideAction extends AbstractCalculationAction {

    public DivideAction(eActionType type, String entityName, String propertyName, ArgumentExpression argumentExpression1, ArgumentExpression argumentExpression2) {
        super(type, entityName, propertyName, argumentExpression1, argumentExpression2);
    }

    @Override
    public void invoke(ExecutionContext context) {
       // arg1.getExprssion()
    }
}
