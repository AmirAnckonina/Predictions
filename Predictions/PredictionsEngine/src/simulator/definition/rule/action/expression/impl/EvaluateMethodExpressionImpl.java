package simulator.definition.rule.action.expression.impl;

import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.AbstractMethodExpression;
import simulator.execution.context.api.Context;


public class EvaluateMethodExpressionImpl extends AbstractMethodExpression<Double>{
    public EvaluateMethodExpressionImpl(eExpressionMethod method, Double methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Double getValue(Context context) {
        return null;
    }

    @Override
    public void setValue(Double value) {

    }
}
