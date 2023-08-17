package simulator.definition.rule.action.expression.impl;

import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.AbstractMethodExpression;
import simulator.execution.context.api.ExecutionContext;


public class RandomMethodExpressionImpl extends AbstractMethodExpression<Integer> {
    public RandomMethodExpressionImpl(eExpressionMethod method, Integer methodParameter) {
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
