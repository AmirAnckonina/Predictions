package simulator.definition.rule.action.expression.argumentExpression.api.interfaces;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.execution.context.api.ExecutionContext;

public interface ArgumentExpression {
    Object getValue(ExecutionContext context);

    PropertyType getExpressionReturnedValueType();
}
