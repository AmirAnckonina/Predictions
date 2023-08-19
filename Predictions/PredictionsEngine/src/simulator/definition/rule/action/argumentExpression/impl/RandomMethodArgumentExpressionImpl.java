package simulator.definition.rule.action.argumentExpression.impl;

import simulator.definition.rule.action.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.execution.context.api.ExecutionContext;


public class RandomMethodArgumentExpressionImpl extends AbstractMethodArgumentExpression<Integer> {
    public RandomMethodArgumentExpressionImpl(eExpressionMethod method, Integer methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Integer getValue(ExecutionContext context) {
        return this.methodParameter;
    }

    @Override
    public void setValue(Integer value) {

    }
}
