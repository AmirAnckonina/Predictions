package simulator.definition.rule.action.expression.impl;

import simulator.definition.rule.action.expression.api.Expression;

public class ValueExpressionImpl<T> implements Expression<T> {

    private T value;
    public ValueExpressionImpl(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return null;
    }

    @Override
    public void setValue(T value) {

    }
}
