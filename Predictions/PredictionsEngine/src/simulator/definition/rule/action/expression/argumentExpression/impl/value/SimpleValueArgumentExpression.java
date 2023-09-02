package simulator.definition.rule.action.expression.argumentExpression.impl.value;

import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class SimpleValueArgumentExpression<T> extends AbstractArgumentExpression {

    private T value;
    public SimpleValueArgumentExpression(T value, ePropertyType expressionReturnedValueType) {
        super(expressionReturnedValueType);
        this.value = value;
    }

    @Override
    public T getValue(ExecutionContext context) {
        return this.value;
    }


}
