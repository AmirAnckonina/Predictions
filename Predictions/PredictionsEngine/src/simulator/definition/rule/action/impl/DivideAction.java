package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.AbstractCalculationAction;
import simulator.definition.rule.action.expression.api.Expression;
import simulator.definition.rule.action.utils.eActionType;
import simulator.execution.context.api.Context;

public class DivideAction extends AbstractCalculationAction {

    public DivideAction(eActionType type, String entityName, String propertyName, Expression expression1, Expression expression2) {
        super(type, entityName, propertyName, expression1, expression2);
    }

    @Override
    public void invoke(Context context) {
       // arg1.getExprssion()
    }
}
