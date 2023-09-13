package simulator.definition.rule.action.expression.argumentExpression.impl.value;

import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class SimpleValueArgumentExpression<T> extends AbstractArgumentExpression {

    private T value;
    public SimpleValueArgumentExpression(T value, PropertyType expressionReturnedValueType) {
        super(expressionReturnedValueType);
        this.value = value;
    }

    @Override
    public T getValue(ExecutionContext context) {
        return this.value;
    }


}
