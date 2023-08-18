package simulator.definition.rule.action.expression.impl;

import simulator.definition.rule.action.expression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.api.abstracts.AbstractMethodExpression;
import simulator.execution.context.api.ExecutionContext;


public class EvaluateMethodExpressionImpl extends AbstractMethodExpression<Double> {
    public EvaluateMethodExpressionImpl(eExpressionMethod method, Double methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Double getValue(ExecutionContext context) {
        return null;
    }

    @Override
    public void setValue(Double value) {

    }
}
