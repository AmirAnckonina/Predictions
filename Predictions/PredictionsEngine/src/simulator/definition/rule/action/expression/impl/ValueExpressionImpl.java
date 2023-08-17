package simulator.definition.rule.action.expression.impl;

import simulator.definition.rule.action.expression.api.Expression;
import simulator.execution.context.api.ExecutionContext;

public class ValueExpressionImpl<T> implements Expression<T> {

    private T value;
    public ValueExpressionImpl(T value) {
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