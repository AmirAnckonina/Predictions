package simulator.definition.rule.action.expression.argumentExpression.impl.method;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractEntityPropertyMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class TicksMethodArgumentExpression extends AbstractEntityPropertyMethodArgumentExpression {

    public TicksMethodArgumentExpression(
            eExpressionMethod method,
            ePropertyType expressionReturnedValueType,
            String entityName,
            String propertyName) {

        super(method, expressionReturnedValueType, entityName, propertyName);
    }

    @Override
    public Object getValue(ExecutionContext context) {
        throw new SimulatorRunnerException("Not impl getValue under Ticks MEthod Expression");
    }
}