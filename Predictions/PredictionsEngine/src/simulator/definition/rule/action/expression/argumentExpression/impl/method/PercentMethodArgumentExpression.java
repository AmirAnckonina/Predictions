package simulator.definition.rule.action.expression.argumentExpression.impl.method;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class PercentMethodArgumentExpression extends AbstractMethodArgumentExpression {

    private ArgumentExpression originalValue;
    private ArgumentExpression percentage;
    public PercentMethodArgumentExpression(
            eExpressionMethod method,
            ePropertyType expressionReturnedValueType,
            ArgumentExpression originalValue,
            ArgumentExpression percentage) {

        super(method, expressionReturnedValueType);
        this.originalValue = originalValue;
        this.percentage = percentage;
    }

    @Override
    public Object getValue(ExecutionContext context) {
        throw new SimulatorRunnerException("Not impl percentArgExp...");
    }

}
