package simulator.definition.rule.action.expression.argumentExpression.impl.method;

import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractEntityPropertyMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;


public class EvaluateMethodArgumentExpression extends AbstractEntityPropertyMethodArgumentExpression {


    public EvaluateMethodArgumentExpression(ExpressionMethodType method, PropertyType expressionReturnedValueType, String entityName, String propertyName) {
        super(method, expressionReturnedValueType, entityName, propertyName);
    }

    @Override
    public Object getValue(ExecutionContext context) {
        throw new SimulatorRunnerException("NotImpl evaluate method expression");
    }

}
