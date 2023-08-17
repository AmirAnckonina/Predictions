package simulator.definition.rule.action.expression.impl;

import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.AbstractMethodExpression;
import simulator.execution.context.api.Context;

public class PercentMethodExpressionImpl extends AbstractMethodExpression<Float> {


    public PercentMethodExpressionImpl(eExpressionMethod method, Float methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Float getValue(Context context) {
        return null;
    }

    @Override
    public void setValue(Float value) {

    }
}
