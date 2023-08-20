package simulator.definition.rule.action.argumentExpression.impl;

import simulator.definition.rule.action.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.runner.utils.exceptions.SimulatorRunnerException;

public class PercentMethodArgumentExpressionImpl extends AbstractMethodArgumentExpression<Float> {


    public PercentMethodArgumentExpressionImpl(eExpressionMethod method, Float methodParameter) {
        super(method, methodParameter);
    }

    @Override
    public Float getValue(ExecutionContext context) {
        throw new SimulatorRunnerException("Not impl percentArgExp...");
    }

    @Override
    public void setValue(Float value) {

    }
}
