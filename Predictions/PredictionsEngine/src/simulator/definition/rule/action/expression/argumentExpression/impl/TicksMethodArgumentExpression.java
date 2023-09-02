package simulator.definition.rule.action.expression.argumentExpression.impl;

import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractEntityPropertyMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class TicksMethodArgumentExpression extends AbstractEntityPropertyMethodArgumentExpression {

    public TicksMethodArgumentExpression(eExpressionMethod method, String entityName, String propertyName) {
        super(method, entityName, propertyName);
    }

    @Override
    public Object getValue(ExecutionContext context) {
        throw new SimulatorRunnerException("Not impl getValue under Ticks MEthod Expression");
    }
}
