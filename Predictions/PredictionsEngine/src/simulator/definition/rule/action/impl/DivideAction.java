package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractCalculationAction;
import simulator.definition.rule.action.expression.api.interfaces.Expression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;

public class DivideAction extends AbstractCalculationAction {

    public DivideAction(eActionType type, String entityName, String propertyName, Expression expression1, Expression expression2) {
        super(type, entityName, propertyName, expression1, expression2);
    }

    @Override
    public void invoke(ExecutionContext context) {
       // arg1.getExprssion()
    }
}
