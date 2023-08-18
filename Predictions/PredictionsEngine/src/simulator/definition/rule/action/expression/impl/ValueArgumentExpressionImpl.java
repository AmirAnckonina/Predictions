package simulator.definition.rule.action.expression.impl;

import simulator.definition.rule.action.expression.api.interfaces.ArgumentExpression;
import simulator.execution.context.api.ExecutionContext;

public class ValueArgumentExpressionImpl<T> implements ArgumentExpression<T> {

    private T value;
    public ValueArgumentExpressionImpl(T value) {
        this.value = value;
    }

    @Override
    public T getValue(ExecutionContext context) {
        return this.value;
    }

    @Override
    public void setValue(T value) {

    }
}
