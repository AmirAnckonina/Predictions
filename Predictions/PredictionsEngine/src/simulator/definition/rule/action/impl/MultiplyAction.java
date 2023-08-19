package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.definition.rule.action.api.abstracts.AbstractCalculationAction;
import simulator.execution.context.api.ExecutionContext;

public class MultiplyAction extends AbstractCalculationAction {

    public MultiplyAction(eActionType type, String entityName, String propertyName, ArgumentExpression argumentExpression1, ArgumentExpression argumentExpression2) {
        super(type, entityName, propertyName, argumentExpression1, argumentExpression2);
    }

    @Override
    public void invoke(ExecutionContext context) {
//        Integer = context.getEmtity.getProproeo.getvalue();
//        if it's numeric
//
//        Multiply multExp = new Multiply(new Number(),new Number()).evaluate();
    }
}
